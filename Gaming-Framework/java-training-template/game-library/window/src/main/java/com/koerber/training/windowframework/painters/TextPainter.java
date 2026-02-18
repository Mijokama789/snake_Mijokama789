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
import java.awt.Font;
import java.awt.Graphics;
import java.util.Objects;

/**
 * The {@code TextPainter} writes a text on a field.
 *
 * @author max_mueller
 * @company Körber Pharma Software GmbH
 * @created 24.10.2022
 * @since windowframework 1.1
 */
public class TextPainter implements FieldPainter {
    private Color textColor = null;
    private String text = null;

    private int fontSize = 0;

    private Font font = null;

    public TextPainter(String text, Color textColor, Font font, int fontSize) {
        this.text = Objects.requireNonNullElse(text, "");
        this.textColor = Objects.requireNonNullElse(textColor, FieldPainters.DEFAULT_TXT_COLOR);
        this.font = Objects.requireNonNullElse(font, FieldPainters.DEFAULT_FONT);
        this.fontSize = fontSize;
    }

    @Override
    public void paintField(int x, int y, int fieldSize, Graphics g) {
        g.setColor(textColor);
        g.setFont(font.deriveFont((float) fontSize));
        int height = g.getFontMetrics().getHeight();
        int width = g.getFontMetrics().stringWidth(text);
        int xOffset = (x * fieldSize) + (fieldSize / 2) - (width / 2);
        int yOffset = ((y + 1) * fieldSize) - (fieldSize / 2) + (height / 2) - g.getFontMetrics().getDescent();
        g.drawString(text, xOffset, yOffset);
    }

    @Override
    public int getZIndex() {
        return 30;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }
}