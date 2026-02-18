package com.koerber.training.inputframework.number;

import java.util.Random;

/**
 * @author
 * a Helper class to simplify the generate positive random numbers
 */
public final class Util {

    /**
     * a private constructor so this class can not be instantiated
     */
    private Util() {
    }

    private static Random rand = new Random();

    /**
     * @param min the lower limit of the range
     * @param max the upper limit of the range
     * @return A random number from the range
     */
    public static int rand(int min, int max) {
        return Util.rand.nextInt((max - min) + 1) + min;
    }
}
