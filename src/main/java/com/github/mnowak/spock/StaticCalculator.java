package com.github.mnowak.spock;

/**
 * Created by michal.nowak on 09/09/14.
 */
public class StaticCalculator {
    private StaticCalculator() {
        // no instances allowed
    }

    public static int add(int a, int b) {
        return a + b;
    }

    public static int multiply(int a, int b) {
        return a * b;
    }
}
