package com.calculator_2_9;

public enum Operation {
    START ('S'),
    CONTINIU ('P'),
    DELENIE ('/'),
    UMNOJENIE ('*'),
    SLOJENIE ('+'),
    VICHITANIE ('-'),
    RESET ('C'),
    STOP ('s');

    private char title;
    Operation(char s) {
        this.title = s;
    }

    public char getTitle() {
        return this.title;
    }
}
