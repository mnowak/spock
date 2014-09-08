package com.github.mnowak.spock;

public class Person {

    private final String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean receiveMessage(String message) {
        System.out.println(this.name + " " + message);
        return true;
    }
}
