package com.fintech.jjeondaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fintech.jjeondaproject.dto.user.UserDto;
import com.fintech.jjeondaproject.dto.user.UserLoginDto;
import com.fintech.jjeondaproject.entity.user.UserEntity;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
	boolean existsByAccountId(String accoundId);
	boolean existsById(Long id);
	UserEntity findByAccountId(String accoundId);

	
	
}
