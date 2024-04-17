package com.whotere.rationplanner.domain.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenResolver accessTokenResolver;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    private Filter authenticationFilter;

    @PostConstruct
    private void initializeFilters() {
        authenticationFilter = new JwtAuthenticationFilter(accessTokenResolver, jwtAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/user/**").permitAll()
                .antMatchers(
                        HttpMethod.GET,
                        "/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs", "/v2/api-docs").permitAll()
                .anyRequest().authenticated()
                    .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .httpBasic().disable()
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}