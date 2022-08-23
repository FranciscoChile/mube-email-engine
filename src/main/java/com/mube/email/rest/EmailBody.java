package com.mube.email.rest;

public class EmailBody {
	private String emailTo;
	private String content;
	private String subject;
	

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	@Override
	public String toString() {
		return "EmailBody [email=" + emailTo + ", content=" + content + ", subject=" + subject + "]";
	}
	public String getEmailTo() {
		return emailTo;
	}
	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}
	
	
}
