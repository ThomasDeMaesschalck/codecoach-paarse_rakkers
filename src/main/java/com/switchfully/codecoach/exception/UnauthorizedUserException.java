package com.switchfully.codecoach.exception;

import com.switchfully.codecoach.domain.UserRole;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class UnauthorizedUserException extends ResponseStatusException {
    public UnauthorizedUserException(UserRole[] role)
    {
        super(HttpStatus.NOT_FOUND, "User does not have role " + role.toString());
    }

}
