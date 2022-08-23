package com.mube.email.rest;

public interface EmailService {
	public String sendSimpleMail(EmailBody emailBody);
	public String sendEmailTemplate(EmailTemplate mail);
}
