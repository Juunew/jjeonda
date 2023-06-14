package com.fintech.jjeondaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fintech.jjeondaproject.entity.bank.BankEntity;

@Repository
public interface BankRepository extends JpaRepository<BankEntity, Long> {
	
	
	
}
