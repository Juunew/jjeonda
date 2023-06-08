package com.fintech.jjeondaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fintech.jjeondaproject.entity.AccountEntity;
import java.util.List;


@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
	AccountEntity findByAccountNum(String accountNum);
	
}
