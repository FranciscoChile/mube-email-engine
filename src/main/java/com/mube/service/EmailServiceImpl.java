package com.mube.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.mube.model.Customer;
import com.mube.model.EmailBody;
import com.mube.model.EmailTemplate;

import freemarker.template.Configuration;

@Service
public class EmailServiceImpl implements EmailService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

	@Autowired 
	private JavaMailSender sender;
	
	@Autowired     
	private Configuration fmConfiguration;

	@Autowired
	private CustomerService customerService;


	public String sendSimpleMail(EmailBody emailBody)
    {
 
        try {			
			LOGGER.info("EmailBody: {}", emailBody.toString());

            SimpleMailMessage mailMessage
                = new SimpleMailMessage();
 
            mailMessage.setFrom("no-reply@mube.cl");
            mailMessage.setTo(emailBody.getEmailTo());
            mailMessage.setText(emailBody.getContent());
            mailMessage.setSubject(emailBody.getSubject());
 
            // Sending the mail
            sender.send(mailMessage);
			LOGGER.info("Mail Sent Successfully...");
            return "Mail Sent Successfully...";
        }
 
        // Catch block to handle the exceptions
        catch (Exception e) {
			e.printStackTrace();			
            return "Error while Sending Mail";
        }
    }
	
	public String sendEmailTemplate(EmailTemplate mail) {
		MimeMessage mimeMessage =sender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
	
			mimeMessageHelper.setSubject(mail.getSubject());
			mimeMessageHelper.setTo(mail.getEmailTo());

			Map<String, Object> model = new HashMap<>();
			model.put("name", mail.getName());
			model.put("content", mail.getContent());
			mail.setModel(model);

			String body = geContentFromTemplate(mail.getModel());
			mimeMessageHelper.setText(body, true);
	
			sender.send(mimeMessageHelper.getMimeMessage());
			return "Mail Sent Successfully...";

		   } catch (MessagingException e) {
			   e.printStackTrace();
			   return "Error while Sending Mail";
		   }
	   }
	
	public String geContentFromTemplate(Map < String, Object >model)     { 
        StringBuffer content = new StringBuffer();
 
        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(fmConfiguration.getTemplate("email-template.flth"), model));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
	


	public String sendBulkEmail(EmailTemplate commonFields) {


		List<Customer> list = customerService.findAll();

		for (Customer c : list) {

			EmailTemplate template = new EmailTemplate();

			template.setContent(commonFields.getContent());
			template.setSubject(commonFields.getSubject());
			template.setName(c.getName());
			template.setEmailTo(c.getEmailTo());

			this.sendEmailTemplate(template);

			LOGGER.info("Email para " + c.getEmailTo() + " enviado");
		}


		return "Envío másivo completado con éxito";

	}


}
