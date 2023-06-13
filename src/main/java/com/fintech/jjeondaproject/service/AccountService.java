package com.fintech.jjeondaproject.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AccountService {
	/*private final AccountRepository accountRepository;
	private final BankService bankService;
	
	public List<AccountDto> accountList(){
		List<AccountEntity> accountEntity = accountRepository.findAll();
		return accountEntity.stream().
		        map(m->new AccountDto(
		        		m.getId(),
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
		        .accountId(accountEntity.getId())
		        .userId(accountEntity.getUser().getId())
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
	
	
	// 계좌 등록 메서드
    public AccountDto registerAccount(String accountNum, BankDto bankDto, Long availableAmt) {
        // BankDto 객체에서 필요한 정보 추출
        String bankCode = bankDto.getBankCode();
        String bankName = bankDto.getBankName();

        // 계좌 등록 로직을 수행합니다.
        AccountDto accountDto = new AccountDto(accountNum, bankCode, bankName, availableAmt);

        // 데이터베이스에 계좌를 저장합니다.
        AccountDto savedAccount = accountRepository.save(accountDto);

        // 저장된 계좌 정보를 AccountDto로 변환하여 반환합니다.
        return convertToDto(savedAccount);
    }

    // BankCode에 해당하는 은행 정보를 조회하는 메서드
    public BankDto getBankByCode(String bankCode) {
        return bankService.getBankByCode(bankCode);
    }

    // Account 엔티티를 AccountDto로 변환하는 메서드
    private AccountDto convertToDto(AccountEntity account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountId(account.getAccountId());
        accountDto.setAccountNum(account.getAccountNum());
        accountDto.setBankCode(account.getBankCode());
        accountDto.setBankName(account.getBankName());
        accountDto.setAvailableAmt(account.getAvailableAmt());
        return accountDto;
    }
	
    public void update(AccountEntity accountEntity) {
		accountRepository.save(accountEntity);
	}
	
	public void delete(AccountEntity accountEntity) {
		accountRepository.deleteById(accountEntity);
	}*/
}



// @Autowired 안쓰는 이유: 생성자로 의존성 주입을 받기때문에 (@RequiredArgsConstructor)
// 롬복을 사용하게 되면 해당 클래스의 의존성 관계가 변경되어도 코드 변경이 필요 없기 때문에


// @Transational 쓰는 이유: 없으면 @OneToMany,@ManyToOne등 지연로딩(Lazy Loading)을
// default로 사용하는 엔티티들을 정상적으로 조회할 수 없습니다.