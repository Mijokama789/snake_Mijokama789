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
package com.koerber.training.windowframework.painters;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Objects;

/**
 * The {@code BackgroundPainter} paints a rectangle onto fields.
 *
 * @author max_mueller
 * @company Körber Pharma Software GmbH
 * @created 24.10.2022
 * @since windowframework 1.1
 */
public class BackgroundPainter implements FieldPainter {

    /**
     * The color of the drawn rectangle
     */
    private Color color = null;

    /**
     * Create a new {@link BackgroundPainter}
     *
     * @param color The background color
     */
    public BackgroundPainter(Color color) {
        this.color = Objects.requireNonNullElse(color, FieldPainters.DEFAULT_BG_COLOR);
    }

    @Override
    public void paintField(int x, int y, int fieldSize, Graphics g) {
        g.setColor(this.color);
        g.fillRect(x * fieldSize, y * fieldSize, fieldSize, fieldSize);
    }

    @Override
    public int getZIndex() {
        return 0;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}