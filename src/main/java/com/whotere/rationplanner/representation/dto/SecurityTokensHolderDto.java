package com.whotere.rationplanner.representation.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class SecurityTokensHolderDto {

    private String userId;
    private String accessToken;
    private ZonedDateTime accessTokenExpiresAt;
}
