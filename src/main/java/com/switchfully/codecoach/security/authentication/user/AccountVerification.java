package com.switchfully.codecoach.security.authentication.user;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "account_verification")
public class AccountVerification {
    @Id
    @Column(name = "id")
    private UUID profileId;

    @Column(name = "verification_code", nullable = false)
    private String verificationCode;

    @Column(name = "created_on", nullable = false)
    private LocalDateTime createdOn = LocalDateTime.now();

    public AccountVerification(){

    }

    public AccountVerification(UUID profileId, String verificationCode){
        this.profileId = profileId;
        this.verificationCode = verificationCode;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public UUID getId() {
        return profileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountVerification that = (AccountVerification) o;
        return Objects.equals(profileId, that.profileId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileId);
    }
}
