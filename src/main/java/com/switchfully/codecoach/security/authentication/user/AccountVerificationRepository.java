package com.switchfully.codecoach.security.authentication.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountVerificationRepository extends CrudRepository<AccountVerification, UUID> {
    void deleteAccountVerificationByProfileId(UUID profileId);

    Optional<AccountVerification> findAccountVerificationByProfileId(UUID profileId);
}
