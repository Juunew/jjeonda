package com.fintech.jjeondaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintech.jjeondaproject.entity.book.DetailBookEntity;

import java.util.List;

public interface DetailBookRepository extends JpaRepository<DetailBookEntity, Long> {
    List<DetailBookEntity> findAllByTotalBookId(Long id);
}
