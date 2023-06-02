package com.fintech.jjeondaproject.service;

import com.fintech.jjeondaproject.dto.book.BookReqDto;
import com.fintech.jjeondaproject.entity.BookEntity;
import com.fintech.jjeondaproject.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    public void createBook(BookReqDto dto) {
        BookEntity bookEntity = BookEntity.builder()
                .costType(dto.getCostType())
                .content(dto.getContent())
                .cost(dto.getCost())
                .memo(dto.getMemo())
                .build();

        bookRepository.save(bookEntity);
    }
}
