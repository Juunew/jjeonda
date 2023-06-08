package com.fintech.jjeondaproject.repository;

import com.fintech.jjeondaproject.entity.TotalBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TotalBookRepository extends JpaRepository<TotalBookEntity, Long> {

    TotalBookEntity findAllByUserIdAndYearAndMonth(Long userId, String year, String month);

}
