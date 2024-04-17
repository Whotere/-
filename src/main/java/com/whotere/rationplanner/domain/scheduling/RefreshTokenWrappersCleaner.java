package com.whotere.rationplanner.domain.scheduling;

import com.whotere.rationplanner.data.repository.RefreshTokenWrapperRepository;
import com.whotere.rationplanner.data.model.RefreshTokenWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RefreshTokenWrappersCleaner {

    private final RefreshTokenWrapperRepository refreshTokenWrapperRepository;

    /**
     * Удаляет из базы данных просроченные refresh токены
     */
    @Scheduled(fixedDelay = 172800000)
    public void removeExpiredRefreshTokens() {

        List<RefreshTokenWrapper> refreshTokenWrappers = refreshTokenWrapperRepository.findAll();

        for(RefreshTokenWrapper refreshTokenWrapper : refreshTokenWrappers) {
            if(refreshTokenWrapper.isExpired()) {
                refreshTokenWrapperRepository.delete(refreshTokenWrapper);
            }
        }
    }
}
