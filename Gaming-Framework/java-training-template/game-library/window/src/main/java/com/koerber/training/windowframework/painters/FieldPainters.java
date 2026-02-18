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
import java.awt.Image;

import javax.swing.JLabel;

/**
 * Factory class for painters
 *
 * @author max_mueller
 * @company Körber Pharma Software GmbH
 * @created 20.10.2022
 * @since windowframework 1.1
 */
public final class FieldPainters {

    public static final Color DEFAULT_BG_COLOR = Color.WHITE;
    public static final Color DEFAULT_SHAPE_COLOR = Color.BLACK;
    public static final Color DEFAULT_TXT_COLOR = Color.BLACK;
    public static final Font DEFAULT_FONT = new JLabel().getFont();

    public static final int DEFAULT_FONT_SIZE = 16;

    public static BackgroundPainter backgroundPainter() {
        return new BackgroundPainter(null);
    }

    public static BackgroundPainter backgroundPainter(Color color) {
        return new BackgroundPainter(color);
    }

    public static TextPainter textPainter(String text) {
        return new TextPainter(text, null, null, DEFAULT_FONT_SIZE);
    }

    public static TextPainter textPainter(String text, int fontSize) {
        return new TextPainter(text, null, null, fontSize);
    }

    public static TextPainter textPainter(String text, Color textColor) {
        return new TextPainter(text, textColor, null, DEFAULT_FONT_SIZE);
    }

    public static CirclePainter circlePainter() {
        return new CirclePainter(null);
    }

    public static CirclePainter circlePainter(Color color) {
        return new CirclePainter(color);
    }

    public static ImagePainter imagePainter(Image image) {
        return new ImagePainter(image);
    }
}