package com.Anemoi.MailSession;

import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Anemoi.InvestorRelation.Configuration.ReadPropertiesFile;


import jakarta.inject.Singleton;

@Singleton
public class MailServiceImpl  implements MailService{
	
	private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

	@Override
	public void sendFirstTimeRegistrationMailToUser(Transport transport, String email, String firstName,
			String roleName,String password) {
		
		logger.debug(
				"inside sendFirstTimeRegistrationMailToUser(4 arg) method :recipientEmail is :: " + email);
		logger.debug("cheking thread name " + Thread.currentThread().getName() + "id" + Thread.currentThread().getId());
		Session session = MailSessionInstance.getMailSession();

		logger.debug("got the session object");
		MimeMessage message = new MimeMessage(session);
		String userName = ReadPropertiesFile.readRequestProperty(MailServiceConstant.USER_NAME);
		String password1 = ReadPropertiesFile.readRequestProperty(MailServiceConstant.PWD);
		String userrollName = ReadPropertiesFile.readRequestProperty(MailServiceConstant.ROLLNAME);
		logger.debug("username :: " + userName);
		logger.debug("username :: " + userrollName);
		try {
			message.setFrom(new InternetAddress(userName));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

			message.setSubject(MailServiceConstant.FIRST_TIME_REG_SUBJECT);
			message.setSubject("Sending Notification");

			String content = MailServiceConstant.FIRST_TIME_REG_MAIL_BODY.replace(MailServiceConstant.USER_NAME_$,
					firstName);
			content = content.replace(MailServiceConstant.PASSWORD, password);
			content = content.replace(MailServiceConstant.ROLLNAME, roleName);
			message.setContent(content, MailServiceConstant.CONTENT_TYPE_HTML);
			logger.debug("sending the mail............");
			long start = System.currentTimeMillis();
			transport.sendMessage(message, message.getAllRecipients());
			long end = System.currentTimeMillis();
			logger.debug("mail sent in :" + (end - start) + "ms");
			logger.debug("first time registration mail successfully send to user : " + email);

		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
		
			e.printStackTrace();
		}
	}
		
	}


