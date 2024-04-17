package com.whotere.rationplanner.domain.security;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
public class RefreshTokenResolver implements TokenResolver {

    @Override
    public Optional<String> resolveToken(HttpServletRequest httpServletRequest) {

        if(httpServletRequest.getCookies() == null) {
            return Optional.empty();
        }

        for(Cookie cookie : httpServletRequest.getCookies()) {
            if(cookie.getName().equals("refresh_token")) {
                return Optional.of(cookie.getValue());
            }
        }

        return Optional.empty();
    }
}
