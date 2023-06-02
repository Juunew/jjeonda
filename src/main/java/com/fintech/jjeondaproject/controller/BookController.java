package com.fintech.jjeondaproject.controller;

import com.fintech.jjeondaproject.dto.book.BookReqDto;
import com.fintech.jjeondaproject.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequiredArgsConstructor
// http://localhost:8080/books
@RequestMapping("/books")
@Controller
public class BookController {

    private final BookService bookService;

    @GetMapping("/detail")
    public String getDetailPage() {
        return "book";
    }

    @PostMapping("/details")
    public String createBook(BookReqDto dto) {
        bookService.createBook(dto);

        return "redirect:/";
    }
}
