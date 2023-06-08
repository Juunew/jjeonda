package com.fintech.jjeondaproject.repository;

import com.fintech.jjeondaproject.entity.DetailBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetailBookRepository extends JpaRepository<DetailBookEntity, Long> {
    List<DetailBookEntity> findAllByTotalBookId(Long id);
}
