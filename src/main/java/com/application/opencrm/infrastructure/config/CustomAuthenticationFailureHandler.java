package com.application.opencrm.infrastructure.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Custom {@link AuthenticationFailureHandler} adapted to REST api infrastructure.
 */
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    /**
     * Sets the response status and does not redirect.
     *
     * @param request the request during which the authentication attempt occurred
     * @param response the response
     * @param exception the exception which was thrown to reject the authentication request
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

}
