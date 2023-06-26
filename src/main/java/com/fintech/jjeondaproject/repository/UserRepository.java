package com.fintech.jjeondaproject.repository;

import com.fintech.jjeondaproject.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
