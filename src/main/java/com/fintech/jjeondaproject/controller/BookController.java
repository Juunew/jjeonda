package com.fintech.jjeondaproject.controller;
import com.fintech.jjeondaproject.common.response.ResBody;
import com.fintech.jjeondaproject.dto.book.BookDateQueryDto;
import com.fintech.jjeondaproject.dto.book.BookListDto;
import com.fintech.jjeondaproject.dto.book.BookReqDto;
import com.fintech.jjeondaproject.dto.book.detail.BookDetailReqDto;
import com.fintech.jjeondaproject.dto.book.detail.BookDetailResDto;
import com.fintech.jjeondaproject.dto.book.monthly.BookMonthlyReqDto;
import com.fintech.jjeondaproject.dto.book.monthly.BookMonthlyResDto;
import com.fintech.jjeondaproject.entity.BookEntity;
import com.fintech.jjeondaproject.repository.BookRepository;
import com.fintech.jjeondaproject.service.BookService;
import com.fintech.jjeondaproject.service.BookTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
@RequiredArgsConstructor
@RequestMapping("/books")
@Controller
public class BookController {
    //스프링 부트가 미리 생성해 놓은 객체를 가져다가 자동으로 연결해줌
    private final BookService bookService;
    private final BookTestService bookTestService;
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
    public String getExpenditureDetailForm(@PathVariable Long bookId, Model model) {
        model.addAttribute("bookId", bookId);
        return "book/bookWrite";
    }
    @PostMapping("/expenditure-detail")
    public String createMyExpenditureDetail(@ModelAttribute BookDetailReqDto reqDto,
                                            @ModelAttribute BookDateQueryDto queryDto,
                                            Model model) {
        BookDetailResDto result = bookTestService.createExpenditureDetail(reqDto);
        model.addAttribute("result", result);
        Long userId = result.getUserId();
        return "redirect:/books/list/" + userId + "?year=" + queryDto.getYear() + "&month=" + queryDto.getMonth();
    }
    // 월간 예산 계획 페이지
    @PostMapping("/month-plan")
    public String createMyBookMonthlyPlan(@ModelAttribute BookMonthlyReqDto reqDto, Model model) {
        BookMonthlyResDto result = bookTestService.createMonthlyPlan(reqDto);
        model.addAttribute("result", result);
        return "book/bookBudget";
    }
    @GetMapping("/month-plan")
    public String getMyBookMonthlyPlan(Model model) {
        return "book/bookBudget";
    }
}