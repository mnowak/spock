package com.github.mnowak.spock;

public class StaticCalculator {
    private StaticCalculator() {
        // no instances allowed
    }

    public static int add(int a, int b) {
        return a + b;
    }

}
