package com.fintech.jjeondaproject.controller;


import com.fintech.jjeondaproject.common.UserInfo;
import com.fintech.jjeondaproject.config.annotation.InfoUser;
import com.fintech.jjeondaproject.dto.book.BookDateQueryDto;
import com.fintech.jjeondaproject.dto.book.BookListDto;
import com.fintech.jjeondaproject.dto.book.detail.BookDetailReqDto;
import com.fintech.jjeondaproject.dto.book.detail.BookDetailResDto;
import com.fintech.jjeondaproject.dto.book.monthly.BookMonthlyModDto;
import com.fintech.jjeondaproject.dto.book.monthly.BookMonthlyReqDto;
import com.fintech.jjeondaproject.dto.book.monthly.BookMonthlyResDto;
import com.fintech.jjeondaproject.entity.book.DetailBookEntity;
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
    @GetMapping("/list")
    public String getMyBookList(@InfoUser UserInfo userInfo,
                                @ModelAttribute BookDateQueryDto queryDto,
                                Model model,@RequestParam(name="year",required=false) String year , @RequestParam(name="month" ,required=false) String month) {
        BookListDto bookListDto = bookService.findMyBookList(userInfo, queryDto);
        model.addAttribute("books", bookListDto);
        model.addAttribute("year",year);
        model.addAttribute("month",month);
        return "book/bookView";
    }

    // 가계부 작성 폼
    @GetMapping("/expenditure-detail/{bookId}")
    public String getExpenditureDetailForm(@PathVariable Long bookId, Model model) {
        model.addAttribute("bookId", bookId);
        return "book/bookWrite";
    }

    //가계부 작성하기
    @PostMapping("/expenditure-detail")
    public String createMyExpenditureDetail(@ModelAttribute BookDetailReqDto bookDetailReqDto) {
        BookDetailResDto result = bookService.createExpenditureDetail(bookDetailReqDto);
        return "redirect:/books/list" + "?year=" + result.getYear() + "&month=0" + result.getMonth();
    }

    // 월간 예산 계획 폼

    @GetMapping("/month-plan/{bookId}")
    public String getMyBookMonthlyPlan(@PathVariable Long bookId, Model model) {
        BookMonthlyResDto bookMonthlyPlan = bookService.findMonthlyPlan(bookId);
        model.addAttribute("bookMonthlyPlan", bookMonthlyPlan);
        return "book/bookBudget";
    }
    // 월간 예산 계획 입력
    @PostMapping("/month-plan")
    public String createMyBookMonthlyPlan(@ModelAttribute BookMonthlyReqDto reqDto) {
        BookMonthlyResDto createPlan = bookService.createMonthlyPlan(reqDto);
        return "redirect:/books/list" + "?year=" + createPlan.getYear() + "&month=0" + createPlan.getMonth();
    }


    //가계부 삭제
    @PostMapping("/delete/{detailBookId}")
    public String deleteMyExpenditureDetail(@PathVariable Long detailBookId, @RequestParam(name="year") String year , @RequestParam(name="month") String month) {
        bookService.deleteExpenditureDetail(detailBookId);

        // 클라이언트에서 선택한 년과월
        return "redirect:/books/list?year="+year+"&month="+month;

//        return "redirect:/books/list" + "?year=" + result.getYear() + "&month=0" + result.getMonth();
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