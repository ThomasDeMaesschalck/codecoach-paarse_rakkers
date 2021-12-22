package com.switchfully.codecoach.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmailNotUniqueException extends ResponseStatusException {
    public EmailNotUniqueException(String email) {
        super(HttpStatus.NOT_FOUND, "Email " + email + " is not unique");
    }

}

