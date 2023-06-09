package com.fintech.jjeondaproject.repository;

import com.fintech.jjeondaproject.entity.BankEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<BankEntity, Long> {
	
	
}
