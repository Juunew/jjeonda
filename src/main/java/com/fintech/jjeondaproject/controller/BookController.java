package com.fintech.jjeondaproject.controller;

import com.fintech.jjeondaproject.dto.book.BookDateQueryDto;
import com.fintech.jjeondaproject.dto.book.BookListDto;
import com.fintech.jjeondaproject.dto.book.BookReqDto;
import com.fintech.jjeondaproject.entity.BookEntity;
import com.fintech.jjeondaproject.repository.BookRepository;
import com.fintech.jjeondaproject.service.BookService;
import com.fintech.jjeondaproject.service.BookTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
// http://localhost:8080/books
@RequestMapping("/books")
@Controller
public class BookController {
    //스프링 부트가 미리 생성해 놓은 객체를 가져다가 자동으로 연결해줌
    private final BookService bookService;
    private final BookTestService bookTestService;

    // 레파지토리를 통해서 테이블의 데이터를 가져온다
    private  final BookRepository bookRepository;

    @GetMapping("/view")
    public String bookViewPage(@PathVariable Long userId,
                               @ModelAttribute BookDateQueryDto queryDto,
                               Model model) {
        //데이터를 다 가져올 수 있게됨, bookRepository에 findAll 메서드를 호출해서 books에 데이터가 담기게 되었음
        /*List<BookEntity> books = bookRepository.findAll();
        model.addAttribute("books",books);*/

        BookListDto books = bookTestService.findMyBookList(userId, queryDto);
        model.addAttribute("books", books);
        return "book/bookView";
    }


    @GetMapping("/detail")
    public String getDetailPage() {
        return "book/bookWrite";
    }

    @PostMapping("/details")
    //DTO로 데이터 받아옴
    public String createBook(BookReqDto bookDto) {

        bookService.createBook(bookDto);

        return "redirect:/";
    }

//    @PostMapping("/details")
//    public String createBook(BookReqDto bookDto) {
//        int cost = bookDto.getCost();
//
//        if (bookDto.getCostType().equals("수입")) {
//            // 수입일 경우 balance에 cost를 더한다.
//            bookDto.setCost(cost);
//        } else {
//            // 고정지출이거나 변동지출일 경우 balance에 cost를 뺀다.
//            bookDto.setCost(-cost);
//        }
//
//        bookService.createBook(bookDto);
//
//        return "redirect:/";
//    }

}
