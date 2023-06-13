package com.fintech.jjeondaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintech.jjeondaproject.entity.book.TotalBookEntity;

public interface TotalBookRepository extends JpaRepository<TotalBookEntity, Long> {

    TotalBookEntity findAllByUserIdAndYearAndMonth(Long userId, String year, String month);

}
