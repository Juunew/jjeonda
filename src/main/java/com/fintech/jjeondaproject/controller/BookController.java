package com.fintech.jjeondaproject.controller;

import com.fintech.jjeondaproject.dto.book.BookDateQueryDto;
import com.fintech.jjeondaproject.dto.book.BookListDto;
import com.fintech.jjeondaproject.dto.book.detail.BookDetailReqDto;
import com.fintech.jjeondaproject.dto.book.detail.BookDetailResDto;
import com.fintech.jjeondaproject.dto.book.monthly.BookMonthlyModDto;
import com.fintech.jjeondaproject.dto.book.monthly.BookMonthlyResDto;
import com.fintech.jjeondaproject.entity.DetailBookEntity;
import com.fintech.jjeondaproject.service.BookTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/books")
@Controller
public class BookController {

    private final BookTestService bookService;

    // 가계부 목록 조회
    @GetMapping("/list/{userId}")
    public String getMyBookList(@PathVariable Long userId,
                                @ModelAttribute BookDateQueryDto queryDto,
                                Model model) {
        BookListDto bookListDto = bookService.findMyBookList(userId, queryDto);
        model.addAttribute("books", bookListDto);

        return "book/bookView";
    }
    // 가계부 작성 폼
    @GetMapping("/expenditure-detail/{bookId}")
    public String getExpenditureDetailForm( @ModelAttribute BookDetailResDto bookDetailReqDto, Model model) {
        return "book/bookWrite";
    }

    //가계부 작성하기
    @PostMapping("/expenditure-detail")
    public String createMyExpenditureDetail(@ModelAttribute BookDetailReqDto bookDetailReqDto, @PathVariable("bookId") Long bookId) {
        BookDetailResDto result = bookService.createExpenditureDetail(bookDetailReqDto);
        return "redirect:/list/" + bookDetailReqDto.getUserId();
    }

    // 월간 예산 계획 폼

    @GetMapping("/month-plan/{bookId}")
    public String getMyBookMonthlyPlan(@PathVariable Long bookId, Model model) {
        BookMonthlyResDto bookMonthlyPlan = bookService.findMonthlyPlan(bookId);
        model.addAttribute("bookMonthlyPlan", bookMonthlyPlan);
        return "book/bookBudget";
    }
    // 월간 예산 계획 작성하기
    @PostMapping("/month-plan/{bookId}")
    public String createMyBookMonthlyPlan(@PathVariable Long bookId, @ModelAttribute BookMonthlyModDto modDto) {
        BookMonthlyResDto updatedPlan = bookService.updateMonthlyPlan(bookId, modDto);
        return "redirect:/books/" + bookId;
    }


    //가계부 삭제
    @GetMapping("/delete/{detailBookId}")
    public String deleteMyExpenditureDetail(@PathVariable Long detailBookId) {
        bookService.deleteExpenditureDetail(detailBookId);
        return "redirect:/books/list/{userId}";
    }

    // 가계부 수정 폼
    @GetMapping("/expenditure-detail/{bookId}/")
    public String getExpenditureDetailUpdateForm(@PathVariable Long bookId, @PathVariable Long expenditureId, Model model) {
        BookDetailResDto result = bookService.findExpenditureDetail(expenditureId);
        model.addAttribute("bookId", bookId);
        model.addAttribute("expenditure", result);
        return "book/bookEdit";
    }
}


