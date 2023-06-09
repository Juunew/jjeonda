package com.fintech.jjeondaproject.util.mail;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public interface MailServiceInter {

	String sendSimpleMessage(String to) throws Exception;

	String createKey();

	MimeMessage createMessage(String to) throws MessagingException, UnsupportedEncodingException;

}
