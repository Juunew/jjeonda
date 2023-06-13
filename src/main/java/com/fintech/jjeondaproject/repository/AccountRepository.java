package com.fintech.jjeondaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fintech.jjeondaproject.entity.account.AccountEntity;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {


    List<AccountEntity> findAllByUserId(Long userId);

    List<AccountEntity> findTranDetailByUserIdAndBankId(Long userId, Long bankId);
}
