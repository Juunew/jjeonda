package com.fintech.jjeondaproject.service;

import com.fintech.jjeondaproject.dto.book.BookDateQueryDto;
import com.fintech.jjeondaproject.dto.book.BookListDto;
import com.fintech.jjeondaproject.dto.book.BookReqDto;
import com.fintech.jjeondaproject.entity.book.DetailBookEntity;
import com.fintech.jjeondaproject.entity.book.TotalBookEntity;
import com.fintech.jjeondaproject.repository.BookRepository;
import com.fintech.jjeondaproject.entity.book.DetailBookEntity;
import com.fintech.jjeondaproject.entity.book.TotalBookEntity;
import com.fintech.jjeondaproject.repository.DetailBookRepository;
import com.fintech.jjeondaproject.repository.TotalBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class BookService {

    private final TotalBookRepository totalBookRepository;
    private final DetailBookRepository detailBookRepository;

    @Transactional(readOnly = true)
    public BookListDto findMyBookList(Long userId, BookDateQueryDto queryDto) {
        TotalBookEntity totalBook = totalBookRepository.findAllByUserIdAndYearAndMonth(userId, queryDto.getYear(), queryDto.getMonth());
        List<DetailBookEntity> detailBookEntity = totalBook.getDetailBook();

        return BookListDto.fromEntities(totalBook, detailBookEntity);
    }


}

