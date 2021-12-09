package com.switchfully.codecoach.security.email;


import com.switchfully.codecoach.security.authentication.user.Event;

public interface MessageSender {
    void handle(Event event);
}