package com.whotere.rationplanner.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

/**
 * Обёртка для access токенов
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccessTokenWrapper {

    private String token;

    private User user;
    private ZonedDateTime expiresAt;
}
