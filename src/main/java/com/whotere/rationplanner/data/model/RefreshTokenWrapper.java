package com.whotere.rationplanner.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * Обертка для refresh токенов. Облегчает работу с refresh токенами в коде и позволяет хранить эти токены в базе данных
 */
@Entity
@Table(name = "refresh_tokens")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RefreshTokenWrapper extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String token;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private ZonedDateTime expiresAt;

    public boolean isExpired() {
        return this.expiresAt.isBefore(ZonedDateTime.now());
    }
}