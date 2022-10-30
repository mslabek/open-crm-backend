package com.application.opencrm.user.controller;

import com.application.opencrm.user.dto.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller handling login requests.
 */
@Tag(name = "Authentication", description = "Operations referring to user authentication")
@RestController
public class LoginController {

    /**
     * Authenticates the user. The response contains a session cookie. The response has a status code of 200 when
     * authentication is successful and 401 when unsuccessful.
     *
     * @param loginRequest the object containing the authentication details
     */
    @PostMapping("/login")
    @Operation(summary = "Authenticates the user and returns an authentication cookie", description = "Required authorization role: none", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "JSON object containing the login and password"))
    @ApiResponse(responseCode = "200", description = "Authentication successful. Session cookie is returned as cookie 'JSESSION'. This cookie is used to authenticate the user and needs to be included in subsequent requests.")
    @ApiResponse(responseCode = "401", description = "Authentication unsuccessful", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public void login(@RequestBody LoginRequest loginRequest) {
        // This method is empty because it's just here to be documented in swagger and because it
        // is an exposed endpoint in the security configuration that can be used for authentication.
    }

}
