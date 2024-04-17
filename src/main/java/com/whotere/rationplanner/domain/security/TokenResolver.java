package com.whotere.rationplanner.domain.security;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface TokenResolver {

    Optional<String> resolveToken(HttpServletRequest httpServletRequest);
}
