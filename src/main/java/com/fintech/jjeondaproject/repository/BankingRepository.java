package com.fintech.jjeondaproject.repository;

import com.fintech.jjeondaproject.entity.openBanking.CardTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankingRepository extends JpaRepository<CardTokenEntity, Long> {
}
