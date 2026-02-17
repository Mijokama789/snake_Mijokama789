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

/**
 * A {@code FieldPainter} is responsible for painting one or multiple fields.
 *
 * @author max_mueller
 * @company Körber Pharma Software GmbH
 * @created 20.10.2022
 * @since windowframework 1.1
 */
public interface FieldPainter {
    /**
     * Paint the field at the location (X|Y).
     *
     * @param x         The X coordinate of the field
     * @param y         The Y coordinate of the field
     * @param fieldSize The size of field
     * @param g         The {@link Graphics} used for drawing
     */
    void paintField(int x, int y, int fieldSize, Graphics g);

    /**
     * Get the Z-Index of the painter. Painters with higher Z-Index will be painted after the ones with lower Z-Index.
     *
     * @return The Z-Index
     */
    default int getZIndex() {
        return 1000;
    }
}