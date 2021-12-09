package com.switchfully.codecoach.security.authentication.user.api;

import java.util.Objects;
import java.util.UUID;

public class SecuredUserDto {
    private UUID id;
    private String email;
    private boolean accountEnabled;

    public SecuredUserDto(UUID id, String email, boolean accountEnabled) {
        this.id = id;
        this.email = email;
        this.accountEnabled = accountEnabled;
    }

    public SecuredUserDto() {
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAccountEnabled() {
        return accountEnabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecuredUserDto that = (SecuredUserDto) o;
        return id == that.id &&
                accountEnabled == that.accountEnabled &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, accountEnabled);
    }
}