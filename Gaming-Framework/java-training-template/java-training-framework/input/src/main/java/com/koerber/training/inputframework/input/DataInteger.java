package com.koerber.training.inputframework.input;

/**
 * @author This class is package Private, it is only visible in this package.-
 *         It is used as a Representation for Numbers as Data
 */
class DataInteger implements Data {

    Integer value;

    DataInteger(Integer val) {
        this.value = val;
    }

    @Override
    public boolean isNum() {
        return true;
    }

    @Override
    public String getText() {
        return this.value.toString();
    }

    @Override
    public boolean isInt() {
        return true;
    }

    @Override
    public Double getNum() {
        return new Double(this.value);
    }

    @Override
    public Integer getInteger() {
        return this.value;
    }

}
