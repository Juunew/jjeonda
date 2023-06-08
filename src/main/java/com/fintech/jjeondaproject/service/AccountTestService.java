package com.fintech.jjeondaproject.service;

import com.fintech.jjeondaproject.common.constant.errorType.UserError;
import com.fintech.jjeondaproject.dto.account.AccountListDto;
import com.fintech.jjeondaproject.dto.account.AccountResDto;
import com.fintech.jjeondaproject.entity.AccountEntity;
import com.fintech.jjeondaproject.entity.UserEntity;
import com.fintech.jjeondaproject.exception.UserException;
import com.fintech.jjeondaproject.repository.AccountRepository;
import com.fintech.jjeondaproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class AccountTestService {
	private final AccountRepository accountRepository;
	private final UserRepository userRepository;

	// === 혁준 api === //
	// 계좌 전체 목록 1
	@Transactional(readOnly = true)
	public List<AccountListDto> findMyAccountList(Long userId) {
		return accountRepository.findAllByUserId(userId).stream()
				.map(AccountListDto::fromEntity)
				.collect(Collectors.toList());
	}

	// 설명 돕기 - 계좌 전체 목록 2
	@Transactional(readOnly = true)
	public List<AccountListDto> findMyAccountList2(Long userId) {
		// userId로 로그인 한 user 의 전체 계좌 목록 조회
		List<AccountEntity> accountEntities = accountRepository.findAllByUserId(userId);

		// Entity 를 담아서 view 에 전달할 새로운 dto 선언
		List<AccountListDto> result = new ArrayList<>();

		// 1. 반복문을 통해서 하나의 데이터 뭉텅이를 하나의 dto 로 변환
		// 2. 변환된 하나의 dto 를 List(result)로 묶어주기
		for (AccountEntity accountEntity : accountEntities) {
			AccountListDto listDto = new AccountListDto(accountEntity);
			result.add(listDto);
		}

		// 묶인 List 를 return
		return result;
	}


	@Transactional(readOnly = true)
	public List<AccountResDto> findMyAccountDetail(Long userId, Long bankId) {
		return accountRepository.findTranDetailByUserIdAndBankId(userId, bankId).stream()
				.map(AccountResDto::fromEntity)
				.collect(Collectors.toList());
	}

	// 설명 돕기 - 단일 계좌 거래내역 목록
	@Transactional(readOnly = true)
	public List<AccountResDto> findMyAccountDetail2(Long userId, Long bankId) {
		List<AccountEntity> accountEntities = accountRepository.findTranDetailByUserIdAndBankId(userId, bankId);
		List<AccountResDto> result = new ArrayList<>();

		for (AccountEntity accountEntity : accountEntities) {
			AccountResDto resDto = new AccountResDto(accountEntity);
			result.add(resDto);
		}

		return result;
	}

	// parameter 로 전달받은 userId를 가진 회원이 존재하는지 check
	private UserEntity getUserInfoOrException(Long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new UserException(UserError.USER_NOT_FOUND));
	}
}
