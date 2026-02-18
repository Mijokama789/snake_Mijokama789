package com.koerber.training.inputframework.input;

/**
 * @author Used to represent input from the user
 */
public interface Data {

    /**
     * @return true if the input was a number
     */
    boolean isNum();

    /**
     * @return true if the input was a integer number
     */
    boolean isInt();

    /**
     * @return the word witch the user has Entered
     */
    String getText();

    /**
     * @return The Numerical value of the word, if it is a number otherwise null
     */
    Double getNum();

    /**
     * @return The Numerical value of the word, as integer if it is a number
     *         otherwise null
     */
    Integer getInteger();

}
