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

import java.awt.GraphicsConfiguration;
import java.awt.MouseInfo;

import javax.swing.JFrame;

/**
 * Helper class for setting the window on the right screen.
 *
 * @author max_mueller
 * @company Körber Pharma Software GmbH
 * @created 24.10.2022
 * @since windowframework 1.1
 */
public class AbstractWindow extends JFrame {
    private GraphicsConfiguration gc = null;

    public AbstractWindow() {
        this(MouseInfo.getPointerInfo().getDevice().getDefaultConfiguration());
    }

    private AbstractWindow(GraphicsConfiguration gc) {
        super(gc);
        this.gc = gc;
    }

    protected void moveWindowOnSameScreenAsMouse() {
        JFrame anchor = new JFrame(gc);
        setLocationRelativeTo(anchor);
    }
}