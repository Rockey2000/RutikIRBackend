package com.Anemoi.MailSession;

public class MailServiceConstant {

	public static final String USER_NAME = "email.account.userName";
	public static final String ROLENAME = "email.account.roleName";
	public static final String MAIL_PROPERTIES_FILE = "mail.properties";
	public static final String PWD = "email.account.password";
	public static final String FIRST_TIME_REG_SUBJECT = "Welcome to Investor Relation ";
//	public static final String FIRST_TIME_REG_MAIL_BODY = "<!DOCTYPE html> <html> <head> <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"> <style> body { margin: 0; overflow: hidden; } .mail-container { position: relative; float: left; width: 55%; height: 100vh; margin: 0 27.5%; margin-top: 3vh; } .mail-msg-box { position: relative; float: left; width: 70%; height: auto; background-color: white; border: 1px solid gray; } .heading { position: relative; float: left; width: 100%; height: auto; color: white; font-size: 1.5rem; font-family: Georgia; background-color: #eb8d00; padding: 5px 0;} .body { position: relative; float: left; width: 100%; padding: 2rem; box-sizing: border-box; } .salutation-row > span {color:black;}.salutation-row { position: relative; float: left; width: 100%; font-size: 1rem; } .user-name { font-weight: bold; color: darkgray; font-size: 1rem; text-align: center; } .default-password, .email-otp { position: relative; float: left; width: 100%; color: #60686f; margin-top: 3vh; } .default-password span, .email-otp span { color: black; font-weight: bold; margin-left: 3px; } .footer { position: relative; float: left; width: 100%; height: auto; margin: 3vh 0 5vh; padding: 5px 0; text-align: center; } </style> </head> <body> <div class=\"mail-container\"> <div class=\"mail-msg-box\"> <div class=\"heading\"> <div style=\"font-size: 1.15rem; text-align: center; margin-top: 5px;\">Welcome!<span style=\"font-size: 0.7rem; color: black; margin-left: 5px;margin-top:5px;\">To</span></div> <div style=\"text-align: center;\">Field Assistance Tool</div> </div> <div class=\"body\"> <div class=\"salutation-row\"> <div style=\"font-size: 1.25rem; color: green; text-align: center;\">Congratulation!</div> <div class=\"user-name\">$$UserName$$</div> <div style=\"font-size: 1.15rem; text-align: center; margin-top: 5px;\">You successfully registered into Grading Tool Application.</div> </div> <div class=\"default-password\"> <div style=\"text-align: center;\">Your default password for temporary login is: <span>$$password$$</span></div> </div> <div class=\"email-otp\"> <div style=\"text-align: center;\">OTP to change your password is: <span>$$OTP$$</span></div> </div> </div> <div class=\"footer\"> Go to <a href=\"$$Link$$\">link</a> to download application </div> </div> </div> </body> </html>";
	public static final String FIRST_TIME_REG_MAIL_BODY = "<!DOCTYPE html> <html> <head> <meta name=\"viewport\" "
			+ "content=\"width=device-width, initial-scale=1\"> <style> body { margin: 0; overflow: hidden; } "
			+ ".mail-container { position: relative; float: left; width: 55%; height: 100vh; margin: 0 27.5%; margin-top: 3vh; } "
			+ ".mail-msg-box { position: relative; float: left; width: 70%; height: auto; background-color: white; border: 1px solid gray; } "
			+ ".heading { position: relative; float: left; width: 100%; height: auto; color: white; font-size: 1.5rem; font-family: Georgia; background-color: #92a8d1; padding: 5px 0;} "
			+ ".body { position: relative; float: left; width: 100%; padding: 2rem; box-sizing: border-box; }"
			+ " .salutation-row > span {color:black;}.salutation-row { position: relative; float: left; width: 100%; font-size: 1rem; } "
			+ ".user-name { font-weight: bold; color: darkgray; font-size: 1rem; text-align: center; } "
			+ ".default-password, .email-otp { position: relative; float: left; width: 100%; color: #60686f; margin-top: 3vh; }"
			+ " .default-password span, .email-otp span { color: black; font-weight: bold; margin-left: 3px; }"
			+ " .footer { position: relative; float: left; width: 100%; height: auto; margin: 3vh 0 5vh; padding: 5px 0; text-align: center; } </style> </head>"
			+ " <body> <div class=\"mail-container\"> <div class=\"mail-msg-box\"> <div class=\"heading\"> "
			+ "<div style=\"font-size: 1.15rem; text-align: center; margin-top: 5px;\">"
			+ "Welcome!<span style=\"font-size: 0.7rem; color: red; margin-left: 5px;margin-top:5px;\">"
			+ "</span></div> <div style=\"text-align: center;\">"
			+ "Investor Relation </div> </div> <div class=\"body\"> "
			+ "<div class=\"salutation-row\"> <div style=\"font-size: 1.25rem; color: green; text-align: center;\">"
			+ "Congratulation!</div> <div class=\"user-name\">$$UserName$$</div> <div style=\"font-size: 1.15rem; text-align: center; margin-top: 5px;\">"
			+ "You have successfully registered into Investor Relation.</div> </div> <div class=\"default-password\"> <div style=\"text-align: center;\">"
			+ " </div> </div> <div class=\"default-roleName\"> <div style=\"text-align: center;\">"
			+ "You are assigned as Role :: <span>$$roleName$$</span></div>   "
			+ "Your Temporary Password is  :: <span>$$password$$</span></div> </div> </div></div> </div><div class=\"footer\"> "
			+ " </div> </div> </body> </html>";

	public static final String FIRST_TIME_REG_MAIL_BODY_BUSINESS = "<!DOCTYPE html> <html> <head> <meta name=\"viewport\" "
			+ "content=\"width=device-width, initial-scale=1\"> <style> body { margin: 0; overflow: hidden; } "
			+ ".mail-container { position: relative; float: left; width: 55%; height: 100vh; margin: 0 27.5%; margin-top: 3vh; } "
			+ ".mail-msg-box { position: relative; float: left; width: 70%; height: auto; background-color: white; border: 1px solid gray; } "
			+ ".heading { position: relative; float: left; width: 100%; height: auto; color: white; font-size: 1.5rem; font-family: Georgia; background-color: #eb8d00; padding: 5px 0;} "
			+ ".body { position: relative; float: left; width: 100%; padding: 2rem; box-sizing: border-box; }"
			+ " .salutation-row > span {color:black;}.salutation-row { position: relative; float: left; width: 100%; font-size: 1rem; } "
			+ ".user-name { font-weight: bold; color: darkgray; font-size: 1rem; text-align: center; } "
			+ ".default-password, .email-otp { position: relative; float: left; width: 100%; color: #60686f; margin-top: 3vh; }"
			+ " .default-password span, .email-otp span { color: black; font-weight: bold; margin-left: 3px; }"
			+ " .footer { position: relative; float: left; width: 100%; height: auto; margin: 3vh 0 5vh; padding: 5px 0; text-align: center; } </style> </head>"
			+ " <body> <div class=\"mail-container\"> <div class=\"mail-msg-box\"> <div class=\"heading\"> "
			+ "<div style=\"font-size: 1.15rem; text-align: center; margin-top: 5px;\">"
			+ "Welcome!<span style=\"font-size: 0.7rem; color: black; margin-left: 5px;margin-top:5px;\">"
			+ "</span></div> <div style=\"text-align: center;\">"
			+ "Vendor Qalification Tool</div> </div> <div class=\"body\"> "
			+ "<div class=\"salutation-row\"> <div style=\"font-size: 1.25rem; color: green; text-align: center;\">"
			+ "Congratulation!</div> <div class=\"user-name\">$$UserName$$</div> <div style=\"font-size: 1.15rem; text-align: center; margin-top: 5px;\">"
			+ "You have successfully registered into Investor Relation.</div> </div> <div class=\"default-password\"> <div style=\"text-align: center;\">"
			+ " </div> </div> <div class=\"default-roleName\"> <div style=\"text-align: center;\">"
			+ "You are assigned as Roll :: <span>$$roleName$$</span></div> </div> </div> <div class=\"footer\"> "
			+ " </div> </div> </body> </html>";

//	public static final String FORGOT_PWD_MAIL_BODY = "<!DOCTYPE html> <html> <head> <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"> <style> body { margin: 0; overflow: hidden; } .mail-container { position: relative; float: left; width: 55%; height: 100vh; margin: 0 22.5%; margin-top: 3vh; } .mail-msg-box { position: relative; float: left; width: 70%; margin: 0 15%; height: auto; background-color: white; border: 1px solid gray; } .heading { position: relative; float: left; width: 100%; height: auto; color: white; padding: 15px 0; font-size: 1.5rem; font-family: Georgia; background-color: #eb8d00; text-align: center; } .body { position: relative; float: left; width: 100%; padding: 2rem; box-sizing: border-box; } .salutation-row { position: relative; float: left; width: 100%; font-size: 1rem; } .welcome {font-size: 1.15em;} .user-name { font-weight: bold; color: darkgray; font-size: 1rem; text-align: center; } .default-password, .email-otp { position: relative; float: left; width: 100%; color: #60686f; margin-top: 3vh; } .default-password span, .email-otp span { color: black; font-weight: bold; margin-left: 3px; } .email-otp > div { position: relative; float: left; width: 100%; } @media only screen and (max-width: 480px) {.body {padding: 2rem 0.5em;} .mail-container {width: 90%;margin: 0 5%;}.mail-msg-box {width: 95%;margin: 0 2.5%;}.heading {font-size: 0.9rem;}.welcome {font-size: 1em;}.salutation-row {font-size: 0.9rem;}.default-password, .email-otp {font-size: 0.85em;}}</style> </head> <body> <div class=\"mail-container\"> <div class=\"mail-msg-box\"> <div class=\"heading\"> <div>Field Assistance Tool</div> </div> <div class=\"body\"> <div class=\"salutation-row\"> <div class=\"welcome\" style=\"color: green; text-align: center;\">Hello!</div> <div class=\"user-name\">$$UserName$$</div> </div> <div class=\"email-otp\"> <div style=\"text-align: center;\">OTP for reset password: <span>$$OTP$$</span></div> </div> </div> </div> </div> </body> </html>";

	public static final String USER_NAME_$ = "$$UserName$$";
	public static final String ROLLNAME = "$$roleName$$";
	public static final String CONTENT_TYPE_HTML = "text/html";
	public static final String CREATEDON = "$$createdOn$$";
	public static final String PASSWORD = "$$password$$";

}
