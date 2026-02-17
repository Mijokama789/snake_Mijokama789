/*
 * This file is part of a Körber Pharma Software GmbH project.
 *
 * Copyright (c)
 *    Körber Pharma Software GmbH
 *    All rights reserved.
 *
 * This source file may be managed in different Java package structures,
 * depending on actual usage of the source file by the Copyright holders:
 *
 * for Werum:  com.werum.* or any other Körber Pharma Software owned Internet domain
 *
 * Any use of this file as part of a software system by none Copyright holders
 * is subject to license terms.
 *
 */
package com.koerber.training.windowframework;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JPanel;

import com.koerber.training.windowframework.painters.FieldPainter;

/**
 * The GUI panel with all visible information on it.
 *
 * @author simon_oelerich
 * @company Körber Pharma Software GmbH
 * @created 28.07.2021
 * @since windowframework 1.0
 */
public class GridPanel extends JPanel {

    private final Object fieldsLock = new Object();
    private int sizeX;
    private int sizeY;
    private int fieldSize;
    private Color gridColor;
    private Field[][] fields;

    public GridPanel(int sizeX, int sizeY, int fieldSize) {
        this(sizeX, sizeY, fieldSize, null);
    }

    public GridPanel(int sizeX, int sizeY, int fieldSize, Color gridColor) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.fieldSize = fieldSize;
        this.gridColor = gridColor;
        setSpecs(sizeX, sizeY, fieldSize, gridColor);
    }

    public void setSpecs(int sizeX, int sizeY, int fieldSize, Color gridColor) {
        synchronized (fieldsLock) {
            this.sizeX = sizeX;
            this.sizeY = sizeY;
            this.fieldSize = fieldSize;
            this.gridColor = gridColor;
            this.fields = new Field[sizeX][sizeY];
            for (int ix = 0; ix < sizeX; ix++) {
                for (int iy = 0; iy < sizeY; iy++) {
                    this.fields[ix][iy] = new Field(ix, iy);
                }
            }
        }
        setAllColor(Color.BLACK);
    }

    public void setAllColor(Color color) {
        streamAllFields().forEach(field -> field.setColor(color));
    }

    public void setColorAt(Color color, int x, int y) {
        getFieldAt(x, y).setColor(color);
        repaint();
    }

    public void setTextAt(String text, int x, int y) {
        getFieldAt(x, y).setText(text);
        repaint();
    }

    public Field getFieldAt(int x, int y) {
        return fields[x][y];
    }

    public Stream<Field> streamAllFields() {
        return Arrays.stream(fields).flatMap(Arrays::stream);
    }

    public List<Field> getAllFields() {
        return streamAllFields().collect(Collectors.toList());
    }

    public void resetFields() {
        streamAllFields().forEach(Field::reset);
    }

    public <T extends FieldPainter> T getPainterAt(int x, int y, Class<T> painterCls) {
        return getFieldAt(x, y).getPainter(painterCls);
    }

    public int getSizeX() {
        return this.sizeX;
    }

    public int getSizeY() {
        return this.sizeY;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        synchronized (fieldsLock) {
            for (int x = 0; x < this.sizeX; x++) {
                for (int y = 0; y < this.sizeY; y++) {
                    fields[x][y].applyPainters(fieldSize, g);
                }
            }
            if (this.gridColor != null) {
                g.setColor(this.gridColor);
                for (int x = 0; x < this.sizeX; x++) {
                    for (int y = 0; y < this.sizeY; y++) {
                        g.drawRect(x * this.fieldSize, y * this.fieldSize, this.fieldSize, this.fieldSize);
                    }
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.fieldSize * this.sizeX + 1, this.fieldSize * this.sizeY + 1);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(this.fieldSize * this.sizeX + 1, this.fieldSize * this.sizeY + 1);
    }
}
