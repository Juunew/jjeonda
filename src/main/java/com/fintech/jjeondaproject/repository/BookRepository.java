package com.fintech.jjeondaproject.repository;

import com.fintech.jjeondaproject.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
