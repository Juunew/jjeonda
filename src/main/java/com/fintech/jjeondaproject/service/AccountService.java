package com.fintech.jjeondaproject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.jaxb.SpringDataJaxb.PageRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fintech.jjeondaproject.dto.account.AccountDto;
import com.fintech.jjeondaproject.entity.AccountEntity;
import com.fintech.jjeondaproject.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
//@Transactional(readOnly=true)
public class AccountService {
	private final AccountRepository accountRepository;
	
	public List<AccountDto> accountList(){
		List<AccountEntity> accountEntity = accountRepository.findAll();
		return accountEntity.stream().
		        map(m->new AccountDto(
		        		m.getAccountId(),
		                m.getUser(),
		                m.getBank(),
		                m.getAccountNum(),
		                m.getAvailableAmt(),
		                m.getTranDate(),
		                m.getTranTime(),
		                m.getInoutType(),
		                m.getContent(),
		                m.getTranAmt(),
		                m.getTranAfterAmt()
		                ))
		        .collect(Collectors.toList());
	}
	
	public AccountDto selectOneByAccountId(Long accountId) {
		AccountEntity accountEntity = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("error"));
		AccountDto accountDto = AccountDto.builder()
		        .accountId(accountEntity.getAccountId())
		        .user(accountEntity.getUser())
		        .bank(accountEntity.getBank())
		        .accountNum(accountEntity.getAccountNum())
		        .availableAmt(accountEntity.getAvailableAmt())
		        .tranDate(accountEntity.getTranDate())
		        .tranTime(accountEntity.getTranTime())
		        .inoutType(accountEntity.getInoutType())
		        .content(accountEntity.getContent())
		        .tranAmt(accountEntity.getTranAmt())
		        .tranAfterAmt(accountEntity.getTranAfterAmt())
		        .build();
		return accountDto;
	}
	
	
}



// @Autowired 안쓰는 이유: 생성자로 의존성 주입을 받기때문에 (@RequiredArgsConstructor)
// 롬복을 사용하게 되면 해당 클래스의 의존성 관계가 변경되어도 코드 변경이 필요 없기 때문에


// @Transational 쓰는 이유: 없으면 @OneToMany,@ManyToOne등 지연로딩(Lazy Loading)을
// default로 사용하는 엔티티들을 정상적으로 조회할 수 없습니다.