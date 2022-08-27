package com.mube.service;

import com.mube.model.EmailBody;
import com.mube.model.EmailTemplate;

public interface EmailService {
	public String sendSimpleMail(EmailBody emailBody);
	public String sendEmailTemplate(EmailTemplate mail);
}
