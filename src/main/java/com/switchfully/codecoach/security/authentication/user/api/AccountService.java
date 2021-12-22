package com.switchfully.codecoach.security.authentication.user.api;

import java.util.Optional;

public interface AccountService {
    Optional<? extends Account> findByEmail(String userName);
}
