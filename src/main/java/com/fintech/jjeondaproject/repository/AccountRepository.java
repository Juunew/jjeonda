package com.fintech.jjeondaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fintech.jjeondaproject.entity.account.AccountEntity;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    static final String DELETE_ACCOUNT = "DELETE FROM Account" + "WHERE ID IN (:deleteAccount)";

	List<AccountEntity> findAllByUserId(Long userId);

    List<AccountEntity> findTranDetailByUserIdAndBankId(Long userId, Long bankId);

	List<AccountEntity> findAllById(Long userId);
	
	@Transactional
	@Modifying
	@Query(value = DELETE_ACCOUNT, nativeQuery = true)
	public int deleteAccount(@Param("deleteAccount") Long[] deleteAccount);
}

