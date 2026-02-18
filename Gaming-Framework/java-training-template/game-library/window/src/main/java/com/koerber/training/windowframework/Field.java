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
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.koerber.training.windowframework.painters.BackgroundPainter;
import com.koerber.training.windowframework.painters.CirclePainter;
import com.koerber.training.windowframework.painters.FieldPainter;
import com.koerber.training.windowframework.painters.FieldPainters;
import com.koerber.training.windowframework.painters.ImagePainter;
import com.koerber.training.windowframework.painters.TextPainter;

/**
 * A field is a place on the {@link GridPanel grid}. It has coordinates {@code x} and {@code y}. Multiple
 * {@link FieldPainter painters} can be added to a field to customize the painting of the field.
 *
 * @author max_mueller
 * @company Körber Pharma Software GmbH
 * @created 20.10.2022
 * @since windowframework 1.0
 */
public class Field {

    /**
     * The X coordinate on the grid
     */
    private final int x;

    /**
     * The Y coordinate on the grid
     */
    private final int y;

    /**
     * The map of painters. Each painter type can only appear once.
     */
    private final Map<Class<?>, FieldPainter> painters = new HashMap<>();

    /**
     * Create a new field
     *
     * @param x The X coordinate
     * @param y The Y coordinate
     */
    Field(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Draw a circle with the given color on the field.
     *
     * @param color The circle's color
     * @return The field for chaining
     */
    public Field setCircleColor(Color color) {
        CirclePainter painter = (CirclePainter) painters.computeIfAbsent(CirclePainter.class,
                cls -> FieldPainters.circlePainter());
        painter.setColor(color);
        return this;
    }

    /**
     * Get the field's color
     *
     * @return The field's color
     */
    public Color getColor() {
        BackgroundPainter painter = (BackgroundPainter) painters.computeIfAbsent(BackgroundPainter.class,
                cls -> FieldPainters.backgroundPainter());
        return painter.getColor();
    }

    /**
     * Set the color of the field
     *
     * @param color The new color of the field
     * @return The field for chaining
     */
    public Field setColor(Color color) {
        BackgroundPainter painter = (BackgroundPainter) painters.computeIfAbsent(BackgroundPainter.class,
                cls -> FieldPainters.backgroundPainter());
        painter.setColor(color);
        return this;
    }

    /**
     * Get the color of the field's circle
     *
     * @return The circle's color
     */
    public Color getCirlceColor() {
        CirclePainter painter = (CirclePainter) painters.computeIfAbsent(CirclePainter.class,
                cls -> FieldPainters.circlePainter());
        return painter.getColor();
    }

    /**
     * Get the field's text
     *
     * @return The field's text
     */
    public String getText() {
        TextPainter painter = (TextPainter) painters.computeIfAbsent(TextPainter.class,
                cls -> FieldPainters.textPainter(null));
        return painter.getText();
    }

    /**
     * Draw the given text on the field.
     *
     * @param text The text to draw
     * @return The field for chaining
     */
    public Field setText(String text) {
        TextPainter painter = (TextPainter) painters.computeIfAbsent(TextPainter.class,
                cls -> FieldPainters.textPainter(null));
        painter.setText(text);
        return this;
    }

    /**
     * Get the field's text color
     *
     * @return The field's text color
     */
    public Color getTextColor() {
        TextPainter painter = (TextPainter) painters.computeIfAbsent(TextPainter.class,
                cls -> FieldPainters.textPainter(null));
        return painter.getTextColor();
    }

    /**
     * Set the color of the text drawn onto this field.
     *
     * @param color The text color
     * @return The field for chaining
     */
    public Field setTextColor(Color color) {
        TextPainter painter = (TextPainter) painters.computeIfAbsent(TextPainter.class,
                cls -> FieldPainters.textPainter(null));
        painter.setTextColor(color);
        return this;
    }

    /**
     * Get the field's font
     *
     * @return The field's font
     */
    public Font getFont() {
        TextPainter painter = (TextPainter) painters.computeIfAbsent(TextPainter.class,
                cls -> FieldPainters.textPainter(null));
        return painter.getFont();
    }

    /**
     * Set the font of the text drawn onto this field.
     *
     * @param font The new font of the text
     * @return The field for chaining
     */
    public Field setFont(Font font) {
        TextPainter painter = (TextPainter) painters.computeIfAbsent(TextPainter.class,
                cls -> FieldPainters.textPainter(null));
        painter.setFont(font);
        return this;
    }

    /**
     * Get the field's font size
     *
     * @return The field's font size
     */
    public int getFontSize() {
        TextPainter painter = (TextPainter) painters.computeIfAbsent(TextPainter.class,
                cls -> FieldPainters.textPainter(null));
        return painter.getFontSize();
    }

    /**
     * Set the fontsize of the text drawn onto this field.
     *
     * @param fontSize The new fontsize
     * @return The field for chaining
     */
    public Field setFontSize(int fontSize) {
        TextPainter painter = (TextPainter) painters.computeIfAbsent(TextPainter.class,
                cls -> FieldPainters.textPainter(null));
        painter.setFontSize(fontSize);
        return this;
    }

    /**
     * Get the field's image
     *
     * @return The field's image
     */
    public Image getImage() {
        ImagePainter painter = (ImagePainter) painters.computeIfAbsent(ImagePainter.class,
                cls -> FieldPainters.imagePainter(null));
        return painter.getImage();
    }

    /**
     * Draw an image onto this field
     *
     * @param img The image to draw
     * @return The field for chaining
     */
    public Field setImage(Image img) {
        ImagePainter painter = (ImagePainter) painters.computeIfAbsent(ImagePainter.class,
                cls -> FieldPainters.imagePainter(null));
        painter.setImage(img);
        return this;
    }

    /**
     * Add a painter to this field.
     *
     * @param painterCls The painter's class
     * @param painter    The painter
     * @param <T>        The type of the painter
     */
    public <T extends FieldPainter> void addPainter(Class<T> painterCls, T painter) {
        if (painters.containsKey(painterCls)) {
            throw new IllegalArgumentException("Painter already exists: " + painterCls.getName());
        }
        painters.put(painterCls, painter);
    }

    /**
     * Get the painter instance with the given class.
     *
     * @param painterCls The class of the painter instance
     * @param <T>        The painter's type
     * @return The painter instance
     */
    public <T extends FieldPainter> T getPainter(Class<T> painterCls) {
        return (T) painters.get(painterCls);
    }

    /**
     * Remove the painter with the given class.
     *
     * @param painterCls The class of the painter instance
     * @param <T>        The painter's type
     * @return The painter instance
     */
    public <T extends FieldPainter> T removePainter(Class<T> painterCls) {
        return (T) painters.remove(painterCls);
    }

    /**
     * Get the X coordinate of this field
     *
     * @return The X coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Get the Y coordinate of this field
     *
     * @return The Y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Call each painter with the given graphics
     *
     * @param fieldSize The size of a field
     * @param g         The {@link Graphics} used for drawing
     */
    public void applyPainters(int fieldSize, Graphics g) {
        painters.values().stream().sorted(Comparator.comparingInt(FieldPainter::getZIndex))
                .forEach(painter -> painter.paintField(getX(), getY(), fieldSize, g));
    }

    /**
     * Remove all painters
     */
    public void reset() {
        painters.clear();
    }
}