package com.mube.email.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {
	
	@Autowired
	private EmailService emailPort;
	
	@PostMapping("/send")
	@ResponseBody
	public EmailResponse SendEmail(@RequestBody EmailBody emailBody)  {
		return new EmailResponse(emailPort.sendSimpleMail(emailBody));
	}

	@PostMapping("/sendTemplate")
	@ResponseBody
	public EmailResponse SendEmailTemplate(@RequestBody EmailTemplate email)  {
		return new EmailResponse(emailPort.sendEmailTemplate(email));
	}

	
}
