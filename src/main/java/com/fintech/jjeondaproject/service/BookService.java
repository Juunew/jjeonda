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

    public void createBook(BookReqDto bookDto) {
        BookEntity bookEntity = BookEntity.builder()
                .costType(bookDto.getCostType())
                .content(bookDto.getContent())
                .cost(bookDto.getCost())
                .memo(bookDto.getMemo())
                .build();

        // 남은 예산 계산
//        bookEntity.calculateBalance();

        bookRepository.save(bookEntity);
    }
}
