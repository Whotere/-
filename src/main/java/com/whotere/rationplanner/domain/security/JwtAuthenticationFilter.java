package com.whotere.rationplanner.domain.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

/**
 * Фильтр навешивает на запрос объект Authentication, если в запросе содержится валидный access токен
 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final TokenResolver accessTokenResolver;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        Optional<String> accessTokenOptional = accessTokenResolver.resolveToken((HttpServletRequest) servletRequest);

        if(accessTokenOptional.isPresent()) {

            String accessToken = accessTokenOptional.get();

            try {
                Authentication authentication = jwtAuthenticationProvider.authenticate(
                    new UserAuthentication(accessToken)
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (AuthenticationException ignored) {}
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
