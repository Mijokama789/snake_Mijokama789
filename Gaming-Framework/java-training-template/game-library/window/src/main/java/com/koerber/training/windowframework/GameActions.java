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

/**
 * The main class for interactions with the grid.
 *
 * @author simon_oelerich
 * @company Körber Pharma Software GmbH
 * @created 28.07.2021
 * @since windowframework 1.0
 */
public interface GameActions {

    default void onGridClicked(int x, int y) {
    }

    default void onGridClicked(int x, int y, com.koerber.training.windowframework.MouseButton mouseClick) {
        onGridClicked(x, y);
    }

    default void onGameLoopTick() {
    }

    default void onArrowPressed(com.koerber.training.windowframework.Direction direction) {
    }

    default void onMousePressed(com.koerber.training.windowframework.MouseButton mouseClick) {
    }
}
