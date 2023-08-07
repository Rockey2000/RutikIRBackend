package com.Anemoi.MailSession;

import javax.mail.Transport;

public interface MailService {

	void sendFirstTimeRegistrationMailToUser(Transport transport, String email, String firstName, String roleName,
			String password);

}
