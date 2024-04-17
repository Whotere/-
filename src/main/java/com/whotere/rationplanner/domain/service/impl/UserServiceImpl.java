package com.whotere.rationplanner.domain.service.impl;

import com.whotere.rationplanner.data.model.AccessTokenWrapper;
import com.whotere.rationplanner.data.model.RefreshTokenWrapper;
import com.whotere.rationplanner.data.model.SecurityTokensHolder;
import com.whotere.rationplanner.data.model.User;
import com.whotere.rationplanner.data.repository.UserRepository;
import com.whotere.rationplanner.domain.exception.AuthenticationException;
import com.whotere.rationplanner.domain.exception.EntityNotFoundException;
import com.whotere.rationplanner.domain.service.SecurityTokensService;
import com.whotere.rationplanner.domain.service.UserService;
import com.whotere.rationplanner.domain.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecurityTokensService securityTokensService;

    private final ValidationService validationService;
    private final Validator userValidator;
    private final Validator userUniquenessValidator;

    @Override
    public SecurityTokensHolder register(@NonNull User userData,
                                         HttpServletResponse httpServletResponse) {


        validationService.validateThrowExceptionIfInvalid(userData, userValidator, userUniquenessValidator);

        userData.setPassword(passwordEncoder.encode(userData.getPassword()));

        userRepository.saveAndFlush(userData);

        AccessTokenWrapper accessTokenWrapper = securityTokensService.createAccessTokenWrapper(userData);
        RefreshTokenWrapper refreshTokenWrapper = securityTokensService.createRefreshTokenWrapper(userData);

        securityTokensService.save(refreshTokenWrapper);

        httpServletResponse.addCookie(createRefreshTokenCookie(refreshTokenWrapper));

        return new SecurityTokensHolder(userData, accessTokenWrapper, refreshTokenWrapper);
    }

    @Override
    public SecurityTokensHolder login(@NonNull User userData,
                                      HttpServletResponse httpServletResponse) {

        User user = userRepository.findByUsername(userData.getUsername()).orElseThrow(
                () -> new AuthenticationException("Пользователя с такими логином и паролем не существует")
        );

        if(!passwordEncoder.matches(userData.getPassword(), user.getPassword())) {
            throw new AuthenticationException("Пользователя с такими логином и паролем не существует");
        }

        AccessTokenWrapper accessTokenWrapper = securityTokensService.createAccessTokenWrapper(user);
        RefreshTokenWrapper refreshTokenWrapper = securityTokensService.createRefreshTokenWrapper(user);

        securityTokensService.save(refreshTokenWrapper);

        httpServletResponse.addCookie(createRefreshTokenCookie(refreshTokenWrapper));

        return new SecurityTokensHolder(user, accessTokenWrapper, refreshTokenWrapper);
    }

    public User findById(@NonNull String id) {

        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Пользователь с id \"%s\" не найден", id))
        );
    }

    private Cookie createRefreshTokenCookie(RefreshTokenWrapper refreshTokenWrapper) {

        long maxAgeInSeconds = ChronoUnit.SECONDS.between(ZonedDateTime.now(), refreshTokenWrapper.getExpiresAt());

        Cookie cookie = new Cookie("refresh_token", refreshTokenWrapper.getToken());
        cookie.setSecure(false);
        cookie.setHttpOnly(true);
        cookie.setPath("/api/v1/user/token");
        cookie.setMaxAge((int) maxAgeInSeconds);

        return cookie;
    }
}