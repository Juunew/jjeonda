package com.fintech.jjeondaproject.controller;

import com.fintech.jjeondaproject.dto.card.CardListDto;
import com.fintech.jjeondaproject.dto.card.CardTestDto;
import com.fintech.jjeondaproject.dto.user.UserDto;
import com.fintech.jjeondaproject.repository.CardTestRepository;
import com.fintech.jjeondaproject.service.CardTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cardTest")
public class CardController {
    private  final CardTestService cardTestService;

    @GetMapping
    public String selectCard(Model model){
        List<CardListDto> cardListDto = cardTestService.selectAll();
        model.addAttribute("cardList", cardListDto);
//        System.out.println(cardListDto);
        return "cardTest";
    }
}
