package com.switchfully.codecoach.security.authentication.user.api;

import com.switchfully.codecoach.security.authentication.user.Authority;

import java.util.List;
import java.util.UUID;

public interface Account {
    UUID getId();

    String getEmail();

    String getPassword();
    void setPassword(String encode);

    List<Authority> getAuthorities();

    boolean isAccountEnabled();
    void enableAccount();
}
