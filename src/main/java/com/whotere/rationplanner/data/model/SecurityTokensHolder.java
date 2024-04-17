package com.whotere.rationplanner.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SecurityTokensHolder {

    private User user;

    private AccessTokenWrapper accessTokenWrapper;
    private RefreshTokenWrapper refreshTokenWrapper;

    public String getUserId() {

        if(user == null) return null;
        return user.getId();
    }

    public String getAccessToken() {

        if(accessTokenWrapper == null) return null;
        return accessTokenWrapper.getToken();
    }

    public ZonedDateTime getAccessTokenExpiresAt() {

        if(accessTokenWrapper == null) return null;
        return accessTokenWrapper.getExpiresAt();
    }
}
