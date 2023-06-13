package com.fintech.jjeondaproject.repository.openBankingRepository;

import com.fintech.jjeondaproject.entity.openBanking.OBTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpenBankingRepository extends JpaRepository<OBTokenEntity, Long> {
}
