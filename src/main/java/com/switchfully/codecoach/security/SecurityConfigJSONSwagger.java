package com.switchfully.codecoach.security;

import com.switchfully.codecoach.security.authentication.jwt.JwtGenerator;
import com.switchfully.codecoach.security.authentication.user.SecuredUserService;
import com.switchfully.codecoach.security.authentication.user.api.AccountService;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

@Profile("development")
@EnableWebSecurity(debug = true)
public class SecurityConfigJSONSwagger extends SecurityConfig {
    private final SecuredUserService securedUserService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfigJSONSwagger(SecuredUserService securedUserService,
                                     PasswordEncoder passwordEncoder, JwtGenerator jwtGenerator, AccountService accountService) {
        super(securedUserService, passwordEncoder, jwtGenerator, accountService);

        this.securedUserService = securedUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/v3/api-docs/**",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/webjars/**");
        super.configure(web);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securedUserService).passwordEncoder(passwordEncoder);
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/v3/api-docs/**",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/webjars/**").permitAll()
                .antMatchers(HttpMethod.POST, "/security/**", "/users", "/sessions").permitAll();

        super.configure(http);

    }


}