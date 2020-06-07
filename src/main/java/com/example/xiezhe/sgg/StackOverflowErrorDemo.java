package com.example.xiezhe.sgg;

public class StackOverflowErrorDemo {
    public static void main(String[] args){
        stackOverflowError();
    }

    private static void stackOverflowError() {
        stackOverflowError();
    }
}
