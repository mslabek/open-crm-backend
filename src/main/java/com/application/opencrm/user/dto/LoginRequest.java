package com.application.opencrm.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;


/**
 * Object containing data necessary for authentication.
 */
@Getter
public class LoginRequest {

    @Schema(name = "username", description = "user's username", required = true, example = "Admin")
    private String username;

    @Schema(name = "password", description = "user's password", required = true, example = "pass")
    private String password;

}
