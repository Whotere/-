package com.whotere.rationplanner.representation.dto.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthenticationForm {

    private String username;
    private String password;
}