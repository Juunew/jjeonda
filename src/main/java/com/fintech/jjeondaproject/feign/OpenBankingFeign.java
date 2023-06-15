package com.fintech.jjeondaproject.feign;

import com.fintech.jjeondaproject.dto.openBanking.OBCardsListDto;
import com.fintech.jjeondaproject.dto.openBanking.OpenBankingDto;
//import com.hk.bankingDemo.vo.CardListResponseVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="feign", url="https://testapi.openbanking.or.kr")
public interface OpenBankingFeign {

	// 등록된 URL에 접속해서 값을 받아와서 VO에 넣어준다..

	@PostMapping(path = "/oauth/2.0/token", consumes = "application/x-www-form-urlencoded", produces = "application/json")
	public OpenBankingDto requestToken(@RequestParam("code") String code ,
									   @RequestParam("client_id") String client_id,
									   @RequestParam("client_secret") String client_secret,
									   @RequestParam("redirect_uri") String redirect_uri,
									   @RequestParam("grant_type") String grant_type);
/*
	@GetMapping(path = "/v2.0/cards")//, produces = "application/json")
	public OBCardsListDto requestCardsList(@RequestHeader("Authorization") String accessToken,
										   @RequestParam String bank_tran_id,
										   @RequestParam String user_seq_no,
										   @RequestParam String bank_code_std,
										   @RequestParam String member_bank_code);
										   */
}
