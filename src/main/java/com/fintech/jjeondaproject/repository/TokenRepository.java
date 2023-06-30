package com.fintech.jjeondaproject.repository;

import com.fintech.jjeondaproject.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
}
