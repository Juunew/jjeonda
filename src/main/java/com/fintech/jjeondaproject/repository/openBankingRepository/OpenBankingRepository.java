package com.fintech.jjeondaproject.repository.openBankingRepository;

import com.fintech.jjeondaproject.entity.openBanking.OBTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpenBankingRepository extends JpaRepository<OBTokenEntity, Long> {
    List<OBTokenEntity> findByUserId(Long userId);
}
