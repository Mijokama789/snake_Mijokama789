package com.koerber.training.inputframework.input;

/**
 * @author This class is package Private, it is only visible in this package.-
 *         It is used as a Representation for Text as Data
 */
class DataText implements Data {

    DataText(String value) {
        this.text = value;
    }

    String text;

    @Override
    public boolean isNum() {
        return false;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public Double getNum() {
        return null;
    }

    @Override
    public boolean isInt() {
        return false;
    }

    @Override
    public Integer getInteger() {
        return null;
    }

}
