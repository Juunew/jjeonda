package com.fintech.jjeondaproject.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

public class RegisterMail implements MailServiceInter{
	@Autowired
	JavaMailSender emailsender; // Bean 등록해둔 MailConfig 를 emailsender 라는 이름으로 autowired
	
	private String ePw; // 인증번호
	
	// 메일 내용 작성
//	@Override
//	public MimeMessage createMessage(String to) throws MessagingException, UnsupportedEncodingException{
//		return null;
//	}
}
