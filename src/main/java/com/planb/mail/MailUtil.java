package com.planb.mail;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class MailUtil
{

	public  boolean sendMail(String email, String accessKey)
	{
		String to = email;
		String fromEmailAddress = "gkumar3151991@gmail.com";
		String password="Manit.bhopal4";
		String host = "smtp.gmail.com";
		String port = "587";
		//String msg = accessKey;

		Properties properties = System.getProperties();
        
      

        properties.put("mail.smtp.starttls.enable", "true"); 
        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.user", fromEmailAddress); 
        properties.put("mail.smtp.password",password); 
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        
        Session session = Session.getInstance(properties, new GmailAthenticator(fromEmailAddress,password));
       // session.setDebug(true);
        session.setDebug(false);

		try
		{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmailAddress));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("RedReview-Activate your account");
			String content="<html><body><a href=\"http://localhost:8080/PlanB/activate?email="+email+"&accesskey="+accessKey+"\">click here</a></body></html>";
			message.setContent(content, "text/html; charset=utf-8");
			Transport.send(message);
            return true;
		}
		catch (MessagingException mex)
		{
			mex.printStackTrace();
			return false;
		}

	}
	public  boolean testMail(String email, String accessKey){
		return true;
	}

	public class GmailAthenticator extends Authenticator
	{

		String user;
		String pw;

		public GmailAthenticator(String username, String password)
		{
			super();
			this.user = username;
			this.pw = password;
		}

		@Override
		public PasswordAuthentication getPasswordAuthentication()
		{
			return new PasswordAuthentication(user, pw);
		}
	}
	public static void main(String[] args) {
		MailUtil mailUtil=new MailUtil();
		mailUtil.sendMail("g.kumar.bhopal@gmail.com", "hello");
	}
	
}
