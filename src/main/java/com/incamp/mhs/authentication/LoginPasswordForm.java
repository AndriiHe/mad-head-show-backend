package com.incamp.mhs.authentication;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginPasswordForm {

    @NotBlank
    private String login;

    @NotBlank
    private String password;
}
