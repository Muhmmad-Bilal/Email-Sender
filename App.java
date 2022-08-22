package com.email;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	System.out.println("Preparing");
        String message="Hello Dear , this message for checking security";
        String subject="Sending Email";
        

	String to="";//write here email to 
        String from="";//write here email from
        
        //sendEmail(message,subject,to,from);
        sendAttach(message,subject,to,from);
    }

	private static void sendAttach(String message, String subject, String to, String from) {
		
		String host="smtp.gmail.com";
		Properties property=System.getProperties();
		System.out.println("Properties:"+property);
		//Properties Files
		
		property.put("mail.smtp.host", host);
		property.put("mail.smtp.port", "465");
		property.put("mail.smtp.ssl.enable","true");
		property.put("mail.smtp.auth","true");
		
		// step 1 Session 
		
		Session session=Session.getInstance(property,new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("","");
			}
		
		
		});
		
		session.setDebug(true);
		
		//step 2 compose message
		MimeMessage m=new MimeMessage(session);
		try
		{
			m.setFrom(from);
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			//subject
			m.setSubject(subject);	
			String path="D:\\bilal.jpg";

		
			MimeMultipart mimeMultipart=new MimeMultipart();
			MimeBodyPart testMime=new MimeBodyPart();
			MimeBodyPart fileMime=new MimeBodyPart();	
			try
			{
			
			testMime.setText(message);
			File f=new File(path);
			fileMime.attachFile(f);
			
			mimeMultipart.addBodyPart(testMime);
			mimeMultipart.addBodyPart(fileMime);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
			m.setContent(mimeMultipart);
			//Transfer Email
			Transport.send(m);
			
			System.out.println("Send Successfully");

		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		

		
	}

	private static void sendEmail(String message, String subject, String to, String from) {
		// TODO Auto-generated method stub
		String host="smtp.gmail.com";
		Properties property=System.getProperties();
		System.out.println("Properties:"+property);
		//Properties Files
		
		property.put("mail.smtp.host", host);
		property.put("mail.smtp.port", "465");
		property.put("mail.smtp.ssl.enable","true");
		property.put("mail.smtp.auth","true");
		
		// step 1 Session 
		
		Session session=Session.getInstance(property,new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("","");
			}
		
		
		});
		
		session.setDebug(true);
		
		//step 2 compose message
		
		MimeMessage m=new MimeMessage(session);
	
		try
		{
			m.setFrom(from);
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			//subject
			m.setSubject(subject);
			//message set
			m.setText(message);
			
			//Transfer Email
			Transport.send(m); 	
			System.out.println("Send Successfully");
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
