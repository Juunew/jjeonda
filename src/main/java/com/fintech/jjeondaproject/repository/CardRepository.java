package com.fintech.jjeondaproject.repository;

import com.fintech.jjeondaproject.entity.card.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {
    CardEntity findByCardId(Long cardId);


    List<CardEntity> findByUserIdAndBankId(Long userId, Long bankId);
    List<CardEntity> findByBankId(Long bankId);
    Optional<CardEntity> findFirstByBankId(Long bankId);
    @Query("select distinct c.bank.id from CardEntity c where c.user.id = :userId")
    List<Long> findDistinctBankIdByUserId(@Param("userId") Long userId);

    List<CardEntity> findByUserId(Long userId);

}
