package com.fintech.jjeondaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fintech.jjeondaproject.entity.card.CardEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {
    CardEntity findByCardId(Long cardId);

    List<CardEntity> findByBankId(long bank_id);

    List<CardEntity> findByUserId(Long userId);

//    Optional<CardEntity> findAllBy();

//    @Query(value = "select card_id from card where id= :id", nativeQuery = true)
//    List<CardEntity> selectCard_id(@Param("id") Long id);

}
