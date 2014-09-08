package com.github.mnowak.spock;


public class EmailServiceImpl implements EmailService {

    @Override
    public boolean send(Person recipient, String message) {
        return recipient.receiveMessage(message);
    }
}
