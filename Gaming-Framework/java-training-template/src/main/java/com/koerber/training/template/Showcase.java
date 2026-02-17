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

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.koerber.training.windowframework.Direction;
import com.koerber.training.windowframework.GameActions;
import com.koerber.training.windowframework.GameWindow;
import com.koerber.training.windowframework.MouseButton;
/**
//// * An example for the use of the GameWindow and the WindowFramework.
//// * Your can adopt the functions and methods accordingly.
//// *
//// * @author simon_oelerich
//// * @company Körber Pharma Software GmbH
//// * @created 28.07.2021
//// * @since windowframework 1.0
//// */
public class Showcase implements GameActions {

    protected final GameWindow gameWindow;
    Direction SnakeDirection;
    int x = 15;
    int y = 15;
    int apfelX = 30;
    int apfelY = 30;
    int SiyeX = 30;
    int SiyeY = 30;
    List<int[]> snake = new ArrayList<>();
    int pendingGrowth= 0;
    int score = 0;
    static int level = 300;

    // entrypoint of application
    public Showcase() {
        this.gameWindow = new GameWindow("Showcase", SiyeX, SiyeY, 20, Color.GRAY, this);
        this.gameWindow.setAllColor(Color.WHITE);
        this.gameWindow.setColorAt(Color.GREEN, x,y);
        apfelRandom();
        snake.add(new int[]{x,y});
        gameWindow.startGameLoop(level);
    }

  // main function to start application
    public static void main(String[] args) {
        new Showcase();
    }

    // draw red point where mouse left-clicked, a blue point for right click and a yellow point for middle click
    @Override
    public void onGridClicked(int x, int y, MouseButton mouseClick) {
        if (mouseClick == MouseButton.LEFTCLICK) {
            this.gameWindow.setColorAt(Color.RED, x, y);
        } else if (mouseClick == MouseButton.MIDDLECLICK) {
            this.gameWindow.setColorAt(Color.YELLOW, x, y);
        } else {
            this.gameWindow.setColorAt(Color.BLUE, x, y);
        }
        this.gameWindow.setText("Clicked at " + x + ", " + y + " with " + mouseClick);
    }

    // when arrow key is pressed
    @Override
    public void onArrowPressed(Direction dir) {
        //System.out.println("Arrow: " + dir);
        if (dir == Direction.RIGHT && SnakeDirection != Direction.LEFT) {
            SnakeDirection = dir;
        } if (dir == Direction.LEFT && SnakeDirection != Direction.RIGHT) {
            SnakeDirection = dir;
        } if (dir == Direction.UP && SnakeDirection != Direction.DOWN) {
            SnakeDirection = dir;
        } if (dir == Direction.DOWN && SnakeDirection != Direction.UP) {
            SnakeDirection = dir;
        }
    }
    public  void createNewApfel(){
        if (snake.get(0)[0] == apfelX && snake.get(0)[1] == apfelY) {
            pendingGrowth++;
            score++;
            level = Math.max(50, level - 10);
            System.out.println("Score " + score);
            apfelRandom();
            System.out.println(level);
            gameWindow.stopGameLoop();
            gameWindow.startGameLoop(level);
        }

    }
    public void snakehead (){
        if (SnakeDirection == Direction.RIGHT) {
            if (snake.get(0)[0] != 29) {
                System.out.println("Rechts");
                int [] head = snake.get(0);
                int newX = head[0]+1;
                int newY = head[1];

                int[] newHead = new int[]{newX,newY};
                snake.add(0, newHead);

                if (pendingGrowth > 0) {
                    pendingGrowth--;
                }else{
                    snake.remove(snake.size() - 1);
                }
            }
        }

        if (SnakeDirection == Direction.LEFT) {
            if (snake.get(0)[0] != 0) {
                System.out.println("Left");
                int [] head = snake.get(0);
                int newX = head[0]-1;
                int newY = head[1];

                int[] newHead = new int[]{newX,newY};
                snake.add(0, newHead);

                if (pendingGrowth > 0) {
                    pendingGrowth--;
                }else{
                    snake.remove(snake.size() - 1);
                }
            }
        }
        if (SnakeDirection == Direction.UP) {
            if (snake.get(0)[1] != 0) {
                System.out.println("up");
                int [] head = snake.get(0);
                int newX = head[0];
                int newY = head[1]-1;

                int[] newHead = new int[]{newX,newY};
                snake.add(0, newHead);

                if (pendingGrowth > 0) {
                    pendingGrowth--;
                }else{
                    snake.remove(snake.size() - 1);
                }
            }
        }
        if (SnakeDirection == Direction.DOWN) {
            if (snake.get(0)[1] != 29) {
                System.out.println("Down");
                int [] head = snake.get(0);
                int newX = head[0];
                int newY = head[1]+1;

                int[] newHead = new int[]{newX,newY};
                snake.add(0, newHead);

                if (pendingGrowth > 0) {
                    pendingGrowth--;
                }else{
                    snake.remove(snake.size() - 1);
                }
            }
        }

    }

    @Override
    public void onGameLoopTick() {
        this.gameWindow.setAllColor(Color.WHITE);
       snakeMove();
       drawsSnake();
       createNewApfel();
       this.gameWindow.setColorAt(Color.RED, apfelX ,apfelY);
//
    }

    public void drawsSnake() {
        for (int i = 0; i < snake.size(); i++) {
            int[] blok = snake.get(i);
            if (i == 0) {
                this.gameWindow.setColorAt(Color.gray, blok[0] , blok[1]);
            } else {
                this.gameWindow.setColorAt(Color.green , blok[0] , blok[1]);
            }

        }
    }




    Random random = new Random();


    public  void apfelRandom(){
        Random random = new Random();
        apfelX = new Random().nextInt(20);
        apfelY = new Random().nextInt(20);
        this.gameWindow.setColorAt(Color.RED, apfelX ,apfelY);
    }
    public void snakeMove() {
        int[] currentHead = snake.get(0);
        int newX = currentHead[0];
        int newY = currentHead[1];

        if (SnakeDirection == Direction.RIGHT) {
            newX = (currentHead[0] + 1) % 30;

        } else if (SnakeDirection == Direction.LEFT) {
            newX = (currentHead[0] - 1 + 30) % 30;

        } else if (SnakeDirection == Direction.UP ) {

            newY = (currentHead[1] - 1 + SiyeY) % SiyeY;
        } else if (SnakeDirection == Direction.DOWN ) {

            newY = (currentHead[1] + 1) % SiyeY;
        } else {
            return;
        }

      for (int i = 0; i < snake.size(); i++) {
          int[] segment = snake.get(i);
          if (segment[0] == newX && segment[1] == newY) {
              System.out.println("Game Over! Die Schlange hat sich selbst berührt.");
              gameWindow.stopGameLoop();
              return;
          }
      }


        int[] newHead = new int[]{newX, newY};
        snake.add(0, newHead);

        if (pendingGrowth > 0) {
            pendingGrowth--;
        } else {
            snake.remove(snake.size() - 1);
        }
    }
}











