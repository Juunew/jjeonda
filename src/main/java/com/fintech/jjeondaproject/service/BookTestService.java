package com.fintech.jjeondaproject.service;

import com.fintech.jjeondaproject.common.constant.errorType.BookError;
import com.fintech.jjeondaproject.dto.book.*;
import com.fintech.jjeondaproject.dto.book.BookListDto;
import com.fintech.jjeondaproject.dto.book.detail.BookDetailModDto;
import com.fintech.jjeondaproject.dto.book.detail.BookDetailReqDto;
import com.fintech.jjeondaproject.dto.book.detail.BookDetailResDto;
import com.fintech.jjeondaproject.dto.book.monthly.BookMonthlyModDto;
import com.fintech.jjeondaproject.dto.book.monthly.BookMonthlyReqDto;
import com.fintech.jjeondaproject.dto.book.monthly.BookMonthlyResDto;
import com.fintech.jjeondaproject.entity.DetailBookEntity;
import com.fintech.jjeondaproject.entity.TotalBookEntity;
import com.fintech.jjeondaproject.exception.BookException;
import com.fintech.jjeondaproject.repository.DetailBookRepository;
import com.fintech.jjeondaproject.repository.TotalBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class BookTestService {

    private final TotalBookRepository totalBookRepository;
    private final DetailBookRepository detailBookRepository;

    @Transactional(readOnly = true)
    public BookListDto findMyBookList(Long userId, BookDateQueryDto queryDto) {
        TotalBookEntity totalBook = totalBookRepository.findAllByUserIdAndYearAndMonth(userId, queryDto.getYear(), queryDto.getMonth());
        List<DetailBookEntity> detailBookEntity = totalBook.getDetailBook();

        return BookListDto.fromEntities(totalBook, detailBookEntity);
    }

    /**
     * 월간 계획
     */

    // return 을 위한 단건 조회
    public BookMonthlyResDto findMonthlyPlan(Long bookId) {
        TotalBookEntity totalBook = getTotalBookInfoOrException(bookId);
        return BookMonthlyResDto.fromEntity(totalBook);
    }

    // 생성
    public BookMonthlyResDto createMonthlyPlan(BookMonthlyReqDto reqDto) {
        return findMonthlyPlan(totalBookRepository.save(TotalBookEntity.of(reqDto)).getId());
    }

    // 수정
    public BookMonthlyResDto updateMonthlyPlan(Long bookId, BookMonthlyModDto modDto) {
        TotalBookEntity totalBook = getTotalBookInfoOrException(bookId);
        totalBook.updateBudget(modDto);

        /**
         * update 시 처리해줘야 할 내용
         *
         * 1. 기존 예산과 기존 남은 예산 이 같을 경우 새로 변경되는 예산으로 남은 예산도 update
         *
         * 2. 기존 남은 예산 > 새로 변경되는 예산 ( 줄어들 경우 )
         *      -> 새로 변경될 남은 예산 -= 기존 예산 - 새로 변경되는 예산
         *
         * 3. 기존 예산 < 새로 변경되는 예산 ( 늘어날 경우 )
         *      1. 새로 변경될 남은 예산 += 새로 변경되는 예산 - 기존 예산
         */

        if (totalBook.getBudget() == totalBook.getRemainBudget()) {
            totalBook.updateRemainBudget(modDto.getBudget());
        } else {
            int budgetDifference = minus(totalBook.getBudget(), modDto.getBudget());

            int remainBudgetUpdate;

            if (totalBook.getBudget() > modDto.getBudget()) {
                remainBudgetUpdate = minus(totalBook.getRemainBudget(), budgetDifference);
                totalBook.updateRemainBudget(remainBudgetUpdate);
            } else {
                remainBudgetUpdate = add(totalBook.getRemainBudget(), -budgetDifference);
                totalBook.updateRemainBudget(remainBudgetUpdate);

            }
        }

        return BookMonthlyResDto.fromEntity(totalBook);
    }

    /**
     * 지출내역
     */

    // return 을 위한 단건 조회
    public BookDetailResDto findExpenditureDetail(Long detailBookId) {
        DetailBookEntity detailBook = getDetailBookInfoOrException(detailBookId);
        return BookDetailResDto.fromEntity(detailBook);
    }

    // 생성
    public BookDetailResDto createExpenditureDetail(BookDetailReqDto reqDto) {
        TotalBookEntity totalBook = getTotalBookInfoOrException(reqDto.getBookId());

        if ("FC".equals(reqDto.getCostType()) || "VC".equals(reqDto.getCostType())) {
            totalBook.updateRemainBudget(minus(totalBook.getRemainBudget(), reqDto.getCost()));
        }

        return findExpenditureDetail(detailBookRepository.save(DetailBookEntity.of(reqDto, totalBook)).getId());
    }

    // 수정
    public BookDetailResDto updateExpenditureDetail(Long detailBookId, BookDetailModDto modDto) {
        DetailBookEntity detailBook = getDetailBookInfoOrException(detailBookId);

        if (modDto != null) {
            detailBook.updateExpenditureDetail(modDto);
        }

        return BookDetailResDto.fromEntity(detailBook);
    }

    private TotalBookEntity getTotalBookInfoOrException(Long bookId) {
        return totalBookRepository.findById(bookId)
                .orElseThrow(() -> new BookException(BookError.BOOK_NOT_FOUND));
    }

    private DetailBookEntity getDetailBookInfoOrException(Long detailBookId) {
        return detailBookRepository.findById(detailBookId)
                .orElseThrow(() -> new BookException(BookError.BOOK_NOT_FOUND));
    }

    private int minus(int valueA, int valueB) {
        return valueA - valueB;
    }

    private int add(int valueA, int valueB) {
        return valueA + valueB;
    }
}
