package com.fintech.jjeondaproject.service;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fintech.jjeondaproject.common.UserInfo;
import com.fintech.jjeondaproject.common.constant.errorType.BankError;
import com.fintech.jjeondaproject.common.constant.errorType.UserError;
import com.fintech.jjeondaproject.config.annotation.InfoUser;
import com.fintech.jjeondaproject.dto.account.AccountDto;
import com.fintech.jjeondaproject.dto.account.AccountReqDto;
import com.fintech.jjeondaproject.dto.account.NoAccountIdDto;
import com.fintech.jjeondaproject.entity.account.AccountEntity;
import com.fintech.jjeondaproject.entity.bank.BankEntity;
import com.fintech.jjeondaproject.entity.user.UserEntity;
import com.fintech.jjeondaproject.exception.BankException;
import com.fintech.jjeondaproject.exception.UserException;
import com.fintech.jjeondaproject.repository.AccountRepository;
import com.fintech.jjeondaproject.repository.BankRepository;
import com.fintech.jjeondaproject.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AccountService {
	private final AccountRepository accountRepository;
	private final UserRepository userRepository;
	private final BankRepository bankRepository;
	
	public List<AccountDto> accountList(UserInfo userInfo){
		List<AccountEntity> accountEntity = accountRepository.findAllByUserId(userInfo.getUserId());

		return accountEntity.stream().
		        map(m->new AccountDto(
		        		m.getId(),
		        		m.getUser().getId(),
		                m.getBank().getId(),
		                m.getBank().getBankName(),
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
		        .bankId(accountEntity.getBank().getId())
		        .bankName(accountEntity.getBank().getBankName())
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
	
	public void addAccount(AccountReqDto reqDto, UserInfo userInfo) {
		   UserEntity user = userRepository.findById(userInfo.getUserId())
		         .orElseThrow(() -> new UserException(UserError.USER_NOT_FOUND));
		   BankEntity bank = bankRepository.findById(reqDto.getBankId())
		         .orElseThrow(() -> new BankException(BankError.BANK_NOT_FOUND));

		   AccountEntity account = AccountEntity.builder()
		         .user(user)
		         .bank(bank)
		         .accountNum(reqDto.getAccountNum())
		         .availableAmt(reqDto.getAvailableAmt())
		         .tranDate(new Date())
		         .tranTime(new Time(System.currentTimeMillis()))
		       /*  .inoutType(noAccountIdDto.getInoutType())
		         .content(noAccountIdDto.getContent())
		         .tranAmt(noAccountIdDto.getTranAmt())
		         .tranAfterAmt(noAccountIdDto.getTranAfterAmt()) */
		         .build();
		   accountRepository.save(account);
		}

	public void deleteAccount(Long accountId) {
			AccountEntity account = accountRepository.findById(accountId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid account ID: " + accountId));
		accountRepository.delete(account);
	}
	
}




/*	    // BankCode에 해당하는 은행 정보를 조회하는 메서드
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
	
	*/

// @Autowired 안쓰는 이유: 생성자로 의존성 주입을 받기때문에 (@RequiredArgsConstructor)
// 롬복을 사용하게 되면 해당 클래스의 의존성 관계가 변경되어도 코드 변경이 필요 없기 때문에


// @Transational 쓰는 이유: 없으면 @OneToMany,@ManyToOne등 지연로딩(Lazy Loading)을
// default로 사용하는 엔티티들을 정상적으로 조회할 수 없습니다.