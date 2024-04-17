package com.whotere.rationplanner.domain.service;

import com.whotere.rationplanner.data.model.SecurityTokensHolder;
import com.whotere.rationplanner.data.model.User;
import com.whotere.rationplanner.data.model.AccessTokenWrapper;
import com.whotere.rationplanner.data.model.RefreshTokenWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SecurityTokensService {

    RefreshTokenWrapper createRefreshTokenWrapper(User user);

    AccessTokenWrapper createAccessTokenWrapper(User user);

    void save(RefreshTokenWrapper refreshTokenWrapper);

    SecurityTokensHolder updateTokens(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
}
