package com.koerber.training.inputframework.input;

/**
 * @author This class is package Private, it is only visible in this package.-
 *         It is used as a Representation for Numbers as Data
 */
class DataNumber implements Data {

    Double value;

    DataNumber(Double val) {
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
        return false;
    }

    @Override
    public Double getNum() {
        return this.value;
    }

    @Override
    public Integer getInteger() {
        return (int) Math.round(this.value);
    }

}
