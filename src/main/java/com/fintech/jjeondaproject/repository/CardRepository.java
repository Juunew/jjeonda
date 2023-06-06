package com.fintech.jjeondaproject.repository;

import com.fintech.jjeondaproject.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {
    CardEntity findByCardId(Long cardId);

    List<CardEntity> findByBankId(long bank_id);

//    Optional<CardEntity> findAllBy();

//    @Query(value = "select card_id from card where id= :id", nativeQuery = true)
//    List<CardEntity> selectCard_id(@Param("id") Long id);

}
