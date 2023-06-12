package com.fintech.jjeondaproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fintech.jjeondaproject.entity.BankEntity;
import com.fintech.jjeondaproject.repository.BankRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BankService {
	private final BankRepository bankRepository;
	
	public List<BankEntity> getAllBanks(){
		return bankRepository.findAll();
	}
	

}
