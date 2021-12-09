package com.switchfully.codecoach.security.authentication.user;

public class ResendVerificationDto {
    private String email;
    private boolean validationBeingResend;

    public String getEmail() {
        return email;
    }

    public boolean isValidationBeingResend() {
        return validationBeingResend;
    }

    public void setValidationBeenResend(boolean validationBeenResend) {
        this.validationBeingResend = validationBeenResend;
    }
}
