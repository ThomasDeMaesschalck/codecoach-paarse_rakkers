/*
package com.switchfully.codecoach.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CoachAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    public static final String NAME_OF_REALM = "All-authenticated-users";

    public CoachAuthenticationEntryPoint() {
        setRealmName(NAME_OF_REALM);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx) {
        response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
*/
