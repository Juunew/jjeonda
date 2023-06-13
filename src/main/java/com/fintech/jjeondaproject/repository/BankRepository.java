package com.fintech.jjeondaproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fintech.jjeondaproject.entity.bank.BankEntity;

@Repository
public interface BankRepository extends JpaRepository<BankEntity, Long> {
//	List<BankEntity> findAllBanks();
	
}
