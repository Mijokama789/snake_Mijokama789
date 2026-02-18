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

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Helper class for handling bags with reference to the grid.
 *
 * @author max_mueller
 * @company Körber Pharma Software GmbH
 * @created 24.10.2022
 * @since windowframework 1.1
 */
public final class GridBagLayoutUtils {

    public static void addToGrid(Container parent, JComponent comp, int x, int y) {
        GridBagLayoutUtils.addToGrid(parent, comp, x, y, 1, 1);
    }

    public static void addToGrid(Container parent, JComponent child, int x, int y, int width, int height) {
        GridBagConstraints constr = new GridBagConstraints();
        constr.gridx = x;
        constr.gridy = y;
        constr.gridwidth = width;
        constr.gridheight = height;

        parent.add(child, constr);
    }

    public static void setPosition(Container parent, JComponent child, int x, int y) {
        GridBagLayout layout = (GridBagLayout) parent.getLayout();
        GridBagConstraints constr = layout.getConstraints(child);
        constr.gridx = x;
        constr.gridy = y;
        layout.setConstraints(child, constr);
    }

    public static void setInsets(Container parent, JComponent child, Insets insets) {
        GridBagLayout layout = (GridBagLayout) parent.getLayout();
        GridBagConstraints constr = layout.getConstraints(child);
        constr.insets = insets;
        layout.setConstraints(child, constr);
    }

    public static void setFill(Container parent, JComponent child, int fill) {
        GridBagLayout layout = (GridBagLayout) parent.getLayout();
        GridBagConstraints constr = layout.getConstraints(child);
        constr.fill = fill;
        layout.setConstraints(child, constr);
    }

    public static void setWeight(Container parent, JComponent child, int wx, int wy) {
        GridBagLayout layout = (GridBagLayout) parent.getLayout();
        GridBagConstraints constr = layout.getConstraints(child);
        constr.weightx = wx;
        constr.weighty = wy;
        layout.setConstraints(child, constr);
    }

    public static void setAnchor(Container parent, JComponent child, int anchor) {
        GridBagLayout layout = (GridBagLayout) parent.getLayout();
        GridBagConstraints constr = layout.getConstraints(child);
        constr.anchor = anchor;
        layout.setConstraints(child, constr);
    }

    public static JComponent spacer(Container parent, int orientation, int x, int y, int size) {
        if (orientation == SwingConstants.HORIZONTAL) {
            JLabel spacer = new JLabel();
            addToGrid(parent, spacer, x, y, 1, size);
            setFill(parent, spacer, GridBagConstraints.HORIZONTAL);
            setWeight(parent, spacer, 1, 0);
            return spacer;
        } else if (orientation == SwingConstants.VERTICAL) {
            JLabel spacer = new JLabel();
            addToGrid(parent, spacer, x, y, size, 1);
            setFill(parent, spacer, GridBagConstraints.VERTICAL);
            setWeight(parent, spacer, 0, 1);
            return spacer;
        } else {
            throw new IllegalArgumentException("Illegal orientation!");
        }
    }
}
