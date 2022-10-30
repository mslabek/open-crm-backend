package com.application.opencrm.infrastructure.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Custom {@link AuthenticationSuccessHandler} adapted to REST api infrastructure.
 */
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * Sets the response status and does not redirect.
     *
     * @param request        the request which caused the successful authentication
     * @param response       the response
     * @param authentication the {@code Authentication} object which was created during the authentication process
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
