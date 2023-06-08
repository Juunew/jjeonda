package com.fintech.jjeondaproject.apiController;

import com.fintech.jjeondaproject.common.response.ResBody;
import com.fintech.jjeondaproject.dto.book.*;
import com.fintech.jjeondaproject.dto.book.BookListDto;
import com.fintech.jjeondaproject.dto.book.detail.BookDetailModDto;
import com.fintech.jjeondaproject.dto.book.detail.BookDetailReqDto;
import com.fintech.jjeondaproject.dto.book.detail.BookDetailResDto;
import com.fintech.jjeondaproject.dto.book.monthly.BookMonthlyModDto;
import com.fintech.jjeondaproject.dto.book.monthly.BookMonthlyReqDto;
import com.fintech.jjeondaproject.dto.book.monthly.BookMonthlyResDto;
import com.fintech.jjeondaproject.service.BookService;
import com.fintech.jjeondaproject.service.BookTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
@RestController
public class BookApiController {

    private final BookTestService bookService;

    // 가계부 목록 조회
    @GetMapping("/list/{userId}")
    public ResBody<?> getMyBookList(@PathVariable Long userId,
                                    @ModelAttribute BookDateQueryDto queryDto) {
        BookListDto result = bookService.findMyBookList(userId, queryDto);
        return ResBody.success(result);
    }

    // 월간 예산 계획
    @PostMapping("/month-plan")
    public ResBody<?> createMyBookMonthlyPlan(@RequestBody BookMonthlyReqDto reqDto) {
        BookMonthlyResDto result = bookService.createMonthlyPlan(reqDto);
        return ResBody.success(result);
    }

    @PutMapping("/month-plan/{bookId}")
    public ResBody<?> modifyMyBookMonthlyPlan(@PathVariable Long bookId,
                                              @RequestBody BookMonthlyModDto modDto) {
        BookMonthlyResDto result = bookService.updateMonthlyPlan(bookId, modDto);
        return ResBody.success(result);
    }

    // 지출 상세 내역
    @PostMapping("/expenditure-detail")
    public ResBody<?> createMyExpenditureDetail(@RequestBody BookDetailReqDto reqDto) {
        BookDetailResDto result = bookService.createExpenditureDetail(reqDto);
        return ResBody.success(result);
    }

    @PutMapping("/expenditure-detail/{detailBookId}")
    public ResBody<?> modifyMyExpenditureDetail(@PathVariable Long detailBookId,
                                                @RequestBody BookDetailModDto modDto) {
        BookDetailResDto result = bookService.updateExpenditureDetail(detailBookId, modDto);
        return ResBody.success(result);
    }
}
