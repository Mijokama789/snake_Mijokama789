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

package com.koerber.training.template;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

import com.koerber.training.windowframework.Direction;
import com.koerber.training.windowframework.GameActions;
import com.koerber.training.windowframework.GameWindow;
import com.koerber.training.windowframework.MouseButton;
import fadecandy.*;

/**
 * An example for the use of the GameWindow and the WindowFramework.
 * Your can adopt the functions and methods accordingly.
 *
 * @author simon_oelerich
 * @company Körber Pharma Software GmbH
 * @created 28.07.2021
 * @since windowframework 1.0
 */
public class Showcase implements GameActions {

    protected final GameWindow gameWindow;
    int width = 10;
    int length = 5;
    Random random = new Random();
    Point snake = new Point(1, 1);
    Point apple = new Point(random.nextInt(width), random.nextInt(length));
    Direction playerDirection = Direction.DOWN;
    LinkedList<Point> snakeList = new LinkedList<>();
    OpcClient server;
    OpcDevice fadecandy;
    PixelStrip strip1;

    // entrypoint of application
    public Showcase() {
        this.server = new OpcClient("localhost", 7890);
        this.fadecandy = server.addDevice();
        this.strip1 = fadecandy.addPixelStrip(0, 50);
        this.gameWindow = new GameWindow("Showcase", width, length, 20, Color.GRAY, this);
        this.gameWindow.setAllColor(Color.WHITE);
        snakeList.add(snake);
        this.gameWindow.setColorAt(Color.green, 1, 1);
        strip1.setPixelColor(led(1, 1), makeColor(139, 34, 34));
        apples();
        this.gameWindow.startGameLoop(500);
        //this.server.show();
    }

    // main function to start application
    public static void main(String[] args) throws InterruptedException {
        Showcase showcase = new Showcase();

        showcase.server.show();
        showcase.server.clear();
        showcase.server.close();


    }

    public void apples() {

        //do {
        apple = new Point(random.nextInt(width), random.nextInt(length));
        //} while (snakeList.contains(apple));

        this.gameWindow.setColorAt(Color.red, apple.x, apple.y);
        strip1.setPixelColor(led(apple.x, apple.y), makeColor(0, 255, 0));
    }


    // draw red point where mouse left-clicked, a blue point for right click and a yellow point for middle click
    @Override
    public void onGridClicked(int x, int y, MouseButton mouseClick) {
    }

    // when arrow key is pressed
    @Override
    public void onArrowPressed(Direction dir) {
        if (dir == Direction.LEFT) {
            playerDirection = Direction.LEFT;
        } else if (dir == Direction.UP) {
            playerDirection = Direction.UP;
        } else if (dir == Direction.RIGHT) {
            playerDirection = Direction.RIGHT;
        } else if (dir == Direction.DOWN) {
            playerDirection = Direction.DOWN;
        }


        System.out.println("Arrow: " + dir);
    }

    // when mouse button is pressed
    @Override
    public void onMousePressed(MouseButton mouseClick) {

    }

    // Repeats every game loop tick
    // The game loop is off by default, enable it with this.gameWindow.startGameLoop(<tickspeed>)
    @Override
    public void onGameLoopTick() {

        if (playerDirection == Direction.LEFT) {

            if (snake.x == 0) {
                snake.x = 9;
            } else {
                snake.x--;

                snakeList.addFirst(new Point(snake.x, snake.y));
            }
        } else if (playerDirection == Direction.UP) {
            if (snake.y == 0) {
                snake.y = 4;
            } else {
                snake.y--;

                snakeList.addFirst(new Point(snake.x, snake.y));
            }
        } else if (playerDirection == Direction.RIGHT) {
            if (snake.x == 9) {
                snake.x = 0;
            } else {
                snake.x++;

                snakeList.addFirst(new Point(snake.x, snake.y));
            }
        } else if (playerDirection == Direction.DOWN) {
            if (snake.y == 4) {
                snake.y = 0;
            } else {
                snake.y++;

                snakeList.addFirst(new Point(snake.x, snake.y));
            }
        }

        //System.out.println(led(snake.x, snake.y));


        this.gameWindow.setAllColor(Color.WHITE);
        strip1.clear();

        this.gameWindow.setColorAt(Color.RED, apple.x, apple.y);
        strip1.setPixelColor(led(apple.x, apple.y), makeColor(0, 255, 0));
        server.show();

        for (Point p : snakeList) {
            this.gameWindow.setColorAt(Color.GREEN, p.x, p.y);
            strip1.setPixelColor(led(p.x, p.y), makeColor(139, 34, 34));
            server.show();
        }

//        System.out.println(snakeList);
        if (this.gameWindow.getFieldAt(snake.x, snake.y) == this.gameWindow.getFieldAt(apple.x, apple.y)) {
            this.gameWindow.setColorAt(Color.white, apple.x, apple.y);
            strip1.setPixelColor(led(apple.x, apple.y), makeColor(0, 0, 0));
            apples();
        } else {

            if (!snakeList.isEmpty()) {
                snakeList.removeLast();
            }
        }
//        this.gameWindow.setAllColor(Color.WHITE);
//        strip1.clear();
//
//        this.gameWindow.setColorAt(Color.RED, apple.x, apple.y);
//        strip1.setPixelColor(led(apple.x,apple.y), makeColor(0, 255, 0));
//        server.show();
//
//        for (Point p : snakeList) {
//            this.gameWindow.setColorAt(Color.GREEN, p.x, p.y);
//            strip1.setPixelColor(led(p.x, p.y), makeColor( 139, 34, 34));
//            server.show();
//        }
    }

    static public int led(int x, int y) {

        int led = 0;

        if (y == 0) {
            led = x + 40;
        } else if (y == 1) {
            led = 39 - x;
        } else if (y == 2) {
            led = x + 20;
        } else if (y == 3) {
            led = 19 - x;
        } else if (y == 4) {
            led = x;
        }
        return led;
    }

    final public static int makeColor(int red, int green, int blue) {
        assert red >= 0 && red <= 255;
        assert green >= 0 && green <= 255;
        assert blue >= 0 && red <= 255;
        int r = red & 0x000000FF;
        int g = green & 0x000000FF;
        int b = blue & 0x000000FF;
        return (r << 16) | (g << 8) | (b);
    }
}
