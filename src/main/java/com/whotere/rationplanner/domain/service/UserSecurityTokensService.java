package com.whotere.rationplanner.domain.service;

import com.whotere.rationplanner.data.model.User;
import com.whotere.rationplanner.data.model.AccessTokenWrapper;
import com.whotere.rationplanner.data.model.RefreshTokenWrapper;

public interface UserSecurityTokensService {

    RefreshTokenWrapper createRefreshToken(User user);

    AccessTokenWrapper createAccessToken(User user);


}
