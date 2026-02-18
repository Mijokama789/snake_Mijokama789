package com.koerber.training.inputframework.input;

import java.util.Scanner;

/**
 * @author A "static" class to get input word by word from the user.
 */
public final class Input { // Final, so no one can extend this class

    private Input() {
    }// A private constructor so no instances can be made

    static Scanner scanner = new Scanner(System.in); // the Scanner class is used to handle the input for the user.

    /**
     * @return The Data representation the next word entered by the user represented
     *         as a Data
     */
    public static Data getInput() {
        String word = Input.scanner.next(); // get the next word from the stream, it will wait till a word is entered

        Data toret; // Convert it to a Data
        try {
            toret = new DataInteger(Integer.parseInt(word)); // can we fit it in an int?
        } catch (NumberFormatException e1) {
            try {
                toret = new DataNumber(Double.parseDouble(word)); // can we fit it in a double?
            } catch (NumberFormatException e2) {
                // If we can't convert it to a number it will be handled as a Text
                toret = new DataText(word);
            }
        }
        return toret;
    }
}
