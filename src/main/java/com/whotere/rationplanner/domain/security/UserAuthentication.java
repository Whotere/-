package com.whotere.rationplanner.domain.security;

import com.whotere.rationplanner.data.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
public class UserAuthentication implements Authentication {

    private final String accessToken;
    private User user;

    private boolean authenticated;

    public UserAuthentication(String accessToken, User user) {
        this.accessToken = accessToken;
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptySet();
    }

    @Override
    public String getCredentials() {
        return accessToken;
    }

    @Override
    public Object getDetails() {
        return user.getUsername();
    }

    @Override
    public User getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return user.getId();
    }
}
