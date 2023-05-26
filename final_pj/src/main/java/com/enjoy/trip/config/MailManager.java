package com.enjoy.trip.config;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.enjoy.trip.dto.MailDto;

@Component
public class MailManager {
	private final JavaMailSender mailSender;
	
	public MailManager(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	public void oneSendMail(MailDto param) throws Exception {
		
		MimeMessage message = mailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		
		helper.setTo(param.getTo());
		
		helper.setSubject(param.getSubject());
		
		helper.setText(param.getMessage());
		
		mailSender.send(message);
	}
	public void GroupSendMail(MailDto[] params) throws Exception {
		for(MailDto m : params) oneSendMail(m);
	}
}
