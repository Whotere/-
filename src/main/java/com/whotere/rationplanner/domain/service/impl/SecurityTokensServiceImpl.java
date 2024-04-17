package com.whotere.rationplanner.domain.service.impl;

import com.whotere.rationplanner.domain.exception.AuthenticationException;
import com.whotere.rationplanner.data.model.SecurityTokensHolder;
import com.whotere.rationplanner.data.model.User;
import com.whotere.rationplanner.data.repository.RefreshTokenWrapperRepository;
import com.whotere.rationplanner.data.model.AccessTokenWrapper;
import com.whotere.rationplanner.domain.security.JwtTokenProvider;
import com.whotere.rationplanner.data.model.RefreshTokenWrapper;
import com.whotere.rationplanner.domain.security.TokenResolver;
import com.whotere.rationplanner.domain.service.SecurityTokensService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class SecurityTokensServiceImpl implements SecurityTokensService {

    @Value("${access-token.validity-time-in-minutes}")
    private Long accessTokenValidityTimeInMinutes;

    @Value("${refresh-token.validity-time-in-minutes}")
    private Long refreshTokenValidityTimeInMinutes;

    private final RefreshTokenWrapperRepository refreshTokenWrapperRepository;

    private final JwtTokenProvider jwtTokenProvider;
    private final TokenResolver refreshTokenResolver;

    @Override
    public RefreshTokenWrapper createRefreshTokenWrapper(@NonNull User user) {

        String refreshToken = jwtTokenProvider.createToken(user, refreshTokenValidityTimeInMinutes);
        ZonedDateTime expiresAt = ZonedDateTime.now().plusMinutes(refreshTokenValidityTimeInMinutes);

        return new RefreshTokenWrapper(refreshToken, user, expiresAt);
    }

    @Override
    public AccessTokenWrapper createAccessTokenWrapper(@NonNull User user) {

        String accessToken = jwtTokenProvider.createToken(user, accessTokenValidityTimeInMinutes);
        ZonedDateTime expiresAt = ZonedDateTime.now().plusMinutes(accessTokenValidityTimeInMinutes);

        return new AccessTokenWrapper(accessToken, user, expiresAt);
    }

    @Override
    public void save(@NonNull RefreshTokenWrapper refreshTokenWrapper) {
        refreshTokenWrapperRepository.save(refreshTokenWrapper);
    }

    @Override
    public SecurityTokensHolder updateTokens(HttpServletRequest httpServletRequest,
                                             HttpServletResponse httpServletResponse) {

        String refreshToken = refreshTokenResolver.resolveToken(httpServletRequest).orElseThrow(
                () -> new AuthenticationException(
                        "Для обновления токенов безопасности в запросе должен присутствовать refresh токен."
                )
        );

        RefreshTokenWrapper refreshTokenWrapper = refreshTokenWrapperRepository.findByToken(refreshToken).orElseThrow(
                () -> new AuthenticationException("Refresh токен невалиден или просрочен. Войдите в аккаунт заново.")
        );

        if(refreshTokenWrapper.isExpired()) {
            throw new AuthenticationException("Refresh токен просрочен. Войдите в аккаунт заново.");
        }

        User user = refreshTokenWrapper.getUser();

        AccessTokenWrapper newAccessTokenWrapper = createAccessTokenWrapper(user);
        RefreshTokenWrapper newRefreshTokenWrapper = createRefreshTokenWrapper(user);

        refreshTokenWrapperRepository.save(refreshTokenWrapper);

        httpServletResponse.addCookie(createRefreshTokenCookie(newRefreshTokenWrapper));

        return new SecurityTokensHolder(user, newAccessTokenWrapper, newRefreshTokenWrapper);
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
