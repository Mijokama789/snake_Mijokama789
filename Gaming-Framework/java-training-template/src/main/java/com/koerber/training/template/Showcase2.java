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
//

import com.koerber.training.windowframework.Direction;
import com.koerber.training.windowframework.GameActions;
import com.koerber.training.windowframework.GameWindow;
import com.koerber.training.windowframework.MouseButton;

/**
// * An example for the use of the GameWindow and the WindowFramework.
// * Your can adopt the functions and methods accordingly.
// *
// * @author simon_oelerich
// * @company Körber Pharma Software GmbH
// * @created 28.07.2021
// * @since windowframework 1.0
// */


import java.awt.*;
import java.util.function.Predicate;

public class Showcase2 implements GameActions {

    protected GameWindow gameWindow;

    int x = 15;
    int y = 15;

    Point rot1 = new Point(10, 10);
    Point rot2 = new Point(20, 20);
    Point rot3 = new Point(30, 30);
    Point rot4 = new Point(40, 40);
    Point rot5 = new Point(50, 50);

    Point blau1 = new Point(10, 10);
    Point blau2 = new Point(20, 20);
    Point blau3 = new Point(30, 30);
    Point blau4 = new Point(40, 40);
    Point blau5 = new Point(50, 50);

    int zaehlerRot = 0;
    int zaehlerBlau = 0;

    int SiyeX = 3;
    int SiyeY = 3;

    public Showcase2() {
        this.gameWindow = new GameWindow("Showcase", SiyeX, SiyeY, 100, Color.GRAY, this);
        this.gameWindow.setAllColor(Color.WHITE);
        this.gameWindow.startGameLoop(100);
    }

    public static void main(String[] args) {
        new Showcase2();
    }


    @Override
    public void onGridClicked(int x, int y, MouseButton mouseClick) {
        if (mouseClick == MouseButton.LEFTCLICK) {
            this.gameWindow.setColorAt(Color.RED, x, y);
            switch (zaehlerRot) {
                case 0:
                    rot1 = new Point(x, y);
                    zaehlerRot++;
                    break;
                case 1:
                    rot2 = new Point(x, y);
                    zaehlerRot++;
                    break;
                case 2:
                    rot3 = new Point(x, y);
                    zaehlerRot++;
                    break;
                case 3:
                    rot4 = new Point(x, y);
                    zaehlerRot++;
                    break;
                case 4:
                    rot5 = new Point(x, y);
                    zaehlerRot++;
                    break;
            }

            System.out.println();
        } else if (mouseClick == MouseButton.RIGHTCLICK) {
            this.gameWindow.setColorAt(Color.BLUE, x, y);
            switch (zaehlerBlau) {
                case 0:
                    blau1 = new Point(x, y);
                    zaehlerBlau++;
                    break;
                case 1:
                    blau2 = new Point(x, y);
                    zaehlerBlau++;
                    break;
                case 2:
                    blau3 = new Point(x, y);
                    zaehlerBlau++;
                    break;
                case 3:
                    blau4 = new Point(x, y);
                    zaehlerBlau++;
                    break;
                case 4:
                    blau5 = new Point(x, y);
                    zaehlerBlau++;
                    break;
            }

        } else {
            this.gameWindow.setColorAt(Color.YELLOW, x, y);
        }
        this.gameWindow.setText("Clicked at " + x + ", " + y + " with " + mouseClick);
    }

    @Override
    public void onGameLoopTick() {
        rotReihe();
        blauReihe();
        rotSpalte();
        blauSpalte();
        rotdiagonale();
        blaudiagonale();
       if(zaehlerRot == 5 && zaehlerBlau==4) {
           System.out.println("Unetschieden");
           this.gameWindow.stopGameLoop();
       }
    }

    public void rotReihe() {
        if (rot1.y == rot2.y && rot1.y == rot3.y) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if (rot1.y == rot4.y && rot1.y == rot5.y) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if (rot1.y == rot2.y && rot1.y == rot4.y) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if (rot1.y == rot2.y && rot1.y == rot5.y) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if (rot1.y == rot3.y && rot1.y == rot4.y) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if (rot1.y == rot3.y && rot1.y == rot5.y) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if (rot2.y == rot3.y && rot2.y == rot4.y) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if (rot2.y == rot4.y && rot2.y == rot5.y) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }

        if (rot2.y == rot3.y && rot2.y == rot5.y) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }

        if (rot3.y == rot4.y && rot3.y == rot5.y) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
    }
    public void blauReihe() {


        if (blau1.y == blau2.y && blau2.y == blau3.y) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if (blau1.y == blau4.y && blau1.y == blau5.y) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if (blau1.y == blau2.y && blau1.y == blau4.y) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if (blau1.y == blau2.y && blau1.y == blau5.y) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if (blau1.y == blau3.y && blau1.y == blau4.y) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if (blau1.y == blau3.y && blau1.y == blau5.y) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if (blau2.y == blau3.y && blau2.y == blau4.y) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if (blau2.y == blau4.y && blau2.y == blau5.y) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }

        if (blau2.y == blau3.y && blau2.y == blau5.y) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }

        if(blau3.y == blau4.y && blau3.y == blau5.y) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
    }
    public void rotSpalte() {
        if (rot1.x == rot2.x && rot1.x == rot3.x) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if (rot1.x == rot4.x && rot1.x == rot5.x) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if (rot1.x == rot2.x && rot1.x == rot4.x) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if (rot1.x == rot2.x && rot1.x == rot5.x) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if (rot1.x == rot3.x && rot1.x == rot4.x) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if (rot1.x == rot3.x && rot1.x == rot5.x) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if (rot2.x == rot3.x && rot2.x == rot4.x) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if (rot2.x == rot4.x && rot2.x == rot5.x) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }

        if (rot2.x == rot3.x && rot2.x == rot5.x) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }

        if (rot3.x == rot4.x && rot3.x == rot5.x) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
    }
    public void blauSpalte() {
        if (blau1.x == blau2.x && blau2.x == blau3.x) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if (blau1.x == blau4.x && blau1.x == blau5.x) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if (blau1.x == blau2.x && blau1.x == blau4.x) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if (blau1.x == blau2.x && blau1.x == blau5.x) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if (blau1.x == blau3.x && blau1.x == blau4.x) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if (blau1.x == blau3.x && blau1.x == blau5.x) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if (blau2.x == blau3.x && blau2.x == blau4.x) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if (blau2.x == blau4.x && blau2.x == blau5.x) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }

        if (blau2.x == blau3.x && blau2.x == blau5.x) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }

        if(blau3.x == blau4.x && blau3.x == blau5.x) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
    }

    public void rotdiagonale(){
        if(pruefeDiagonale(rot1, rot2, rot3)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale(rot1, rot2, rot4)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale(rot1, rot2, rot5)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale(rot1, rot3, rot4)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale(rot1, rot3, rot5)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale(rot1, rot4, rot5)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale(rot2, rot3, rot4)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale(rot2, rot4, rot5)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale(rot2, rot3, rot5)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale(rot3, rot4, rot5)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }




        if(pruefeDiagonale2(rot1, rot2, rot3)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale2(rot1, rot2, rot4)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale2(rot1, rot2, rot5)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale2(rot1, rot3, rot4)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale2(rot1, rot3, rot5)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale2(rot1, rot4, rot5)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale2(rot2, rot3, rot4)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale2(rot2, rot4, rot5)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale2(rot2, rot3, rot5)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale2(rot3, rot4, rot5)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }

        if(pruefeDiagonale3(rot1, rot2, rot3)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale3(rot1, rot2, rot4)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale3(rot1, rot2, rot5)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale3(rot1, rot3, rot4)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale3(rot1, rot3, rot5)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale3(rot1, rot4, rot5)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale3(rot2, rot3, rot4)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale3(rot2, rot4, rot5)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale3(rot2, rot3, rot5)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale3(rot3, rot4, rot5)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }



        if(pruefeDiagonale4(rot1, rot2, rot3)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale4(rot1, rot2, rot4)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale4(rot1, rot2, rot5)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale4(rot1, rot3, rot4)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale4(rot1, rot3, rot5)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale4(rot1, rot4, rot5)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale4(rot2, rot3, rot4)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale4(rot2, rot4, rot5)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale4(rot2, rot3, rot5)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale4(rot3, rot4, rot5)) {
            System.out.println("rot hat gewonnen");
            this.gameWindow.stopGameLoop();
        }



    }

public boolean pruefeDiagonale(Point a, Point b,Point c ) {
       return c.x == b.x+1 && c.x == a.x+2 && c.y == b.y+1 && c.y == a.y+2;
}


    public boolean pruefeDiagonale2(Point a, Point b,Point c ) {
        return c.x == b.x-1 && c.x == a.x-2 && c.y == b.y-1 && c.y == a.y-2;
    }









    public void blaudiagonale(){
        if(pruefeDiagonale(blau1, blau2, blau3)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale(blau1, blau2, blau4)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale(blau1, blau2, blau5)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale(blau1, blau3, blau4)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale(blau1, blau3, blau5)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale(blau1, blau4, blau5)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale(blau2, blau3, blau4)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale(blau2, blau4, blau5)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale(blau2, blau3, blau5)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale(blau3,blau4, blau5)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }



        if(pruefeDiagonale2(blau1, blau2, blau3)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale2(blau1, blau2, blau4)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale2(blau1, blau2, blau5)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale2(blau1, blau3, blau4)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale2(blau1, blau3, blau5)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale2(blau1, blau4, blau5)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale2(blau2, blau3, blau4)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale2(blau2, blau4, blau5)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale2(blau2, blau3, blau5)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale2(blau3,blau4, blau5)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }




        if(pruefeDiagonale3(blau1, blau2, blau3)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale3(blau1, blau2, blau4)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale3(blau1, blau2, blau5)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale3(blau1, blau3, blau4)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale3(blau1, blau3, blau5)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale3(blau1, blau4, blau5)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale3(blau2, blau3, blau4)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale3(blau2, blau4, blau5)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale3(blau2, blau3, blau5)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale3(blau3,blau4, blau5)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }



        if(pruefeDiagonale4(blau1, blau2, blau3)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale4(blau1, blau2, blau4)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale4(blau1, blau2, blau5)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale4(blau1, blau3, blau4)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale4(blau1, blau3, blau5)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale4(blau1, blau4, blau5)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale4(blau2, blau3, blau4)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale4(blau2, blau4, blau5)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale4(blau2, blau3, blau5)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }
        if(pruefeDiagonale4(blau3,blau4, blau5)) {
            System.out.println("blau hat gewonnen");
            this.gameWindow.stopGameLoop();
        }

    }




   public boolean pruefeDiagonale3(Point a, Point b,Point c ) {
       return c.x == b.x+1 && c.x == a.x+2 && c.y == b.y-1 && c.y == a.y-2;
    }



    public boolean pruefeDiagonale4(Point a, Point b,Point c ) {
        return c.x == b.x-1 && c.x == a.x-2 && c.y == b.y+1 && c.y == a.y+2;
    }

}