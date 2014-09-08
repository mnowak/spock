package com.github.mnowak.spock;

public interface EmailService {

    boolean send(Person recipient, String message);

}
