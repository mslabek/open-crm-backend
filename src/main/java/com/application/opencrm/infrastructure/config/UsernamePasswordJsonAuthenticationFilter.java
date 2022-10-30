package com.application.opencrm.infrastructure.config;

import com.application.opencrm.common.exception.InternalServerException;
import com.application.opencrm.user.dto.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class providing a customized {@link UsernamePasswordAuthenticationFilter}. The bean of this class can be added as
 * a {@link Filter} to the {@link SecurityFilterChain}.
 */
public class UsernamePasswordJsonAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final Log currentLogger = LogFactory.getLog(getClass());

    /**
     * Performs authentication using the provided {@link AuthenticationManager}. The data that is to be authenticated
     * is extracted from the request in the form of {@link LoginRequest}.
     *
     * @param request the object from which to extract parameters and perform the authentication
     * @param response the response which is not actually used in the method but is required to override the method
     *                 in the parent class
     * @return the authenticated user token, or null if authentication is incomplete
     * @throws AuthenticationException if authentication fails
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        currentLogger.debug("Attempting authentication");
        try {
            LoginRequest loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
            UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
            setDetails(request, token);
            return this.getAuthenticationManager().authenticate(token);
        } catch (IOException e) {
            throw new InternalServerException(e.getMessage());
        }
    }
}
