package com.whotere.rationplanner.domain.service;

import com.whotere.rationplanner.data.model.SecurityTokensHolder;
import com.whotere.rationplanner.data.model.User;
import org.springframework.lang.NonNull;

import javax.servlet.http.HttpServletResponse;

public interface UserService {

    SecurityTokensHolder register(@NonNull User userData, HttpServletResponse httpServletResponse);

    SecurityTokensHolder login(@NonNull User userData, HttpServletResponse httpServletResponse);

    User findById(@NonNull String id);
}