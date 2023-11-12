package dev.capstone;

public class SimpleClass {
    private Integer myField;

    public SimpleClass() {
        this.myField = 1;
    }

    public void myFunc(Integer input) {
        myField += input;
    }

    public Integer getMyField() {
        return myField;
    }
}
