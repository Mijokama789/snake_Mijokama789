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

import java.awt.Graphics;
import java.awt.Image;

/**
 * The {@code ImagePainter} paints an image on a field.
 *
 * @author max_mueller
 * @company Körber Pharma Software GmbH
 * @created 24.10.2022
 * @since windowframework 1.1
 */
public class ImagePainter implements FieldPainter {
    private Image image = null;

    public ImagePainter(Image image) {
        this.image = image;
    }

    @Override
    public void paintField(int x, int y, int fieldSize, Graphics g) {
        g.drawImage(image, x * fieldSize, y * fieldSize, fieldSize, fieldSize, null);
    }

    @Override
    public int getZIndex() {
        return 20;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}