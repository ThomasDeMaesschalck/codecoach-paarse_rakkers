package com.switchfully.codecoach.security.authentication.user;

import com.switchfully.codecoach.domain.UserRole;
import com.switchfully.codecoach.security.authentication.SecuredUser;
import com.switchfully.codecoach.security.authentication.user.api.Account;
import com.switchfully.codecoach.security.authentication.user.api.AccountService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;


@Service
@Transactional
public class SecuredUserService implements UserDetailsService {

    private final AccountService accountService;

    public SecuredUserService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {
        Account account = accountService.findByEmail(userName)
                .orElseThrow(() -> new UsernameNotFoundException(userName));

        Collection<UserRole> authorities = determineGrantedAuthorities(account);

        return new SecuredUser(account.getEmail(), account.getPassword(), authorities, account.isAccountEnabled());
    }

    private Collection<UserRole> determineGrantedAuthorities(Account account) {
        return new ArrayList<>(account.getAuthorities());
    }


}
