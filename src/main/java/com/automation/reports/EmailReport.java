package com.automation.reports;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import com.automation.helper.browser.config.ObjectReader;
import com.automation.helper.browser.config.PropertyReader;
import com.automation.helper.logger.LoggerHelper;
import com.automation.helper.resource.ResourcePath;
import com.automation.utils.FileUtilities;

public class EmailReport {
	
	private static Logger log = LoggerHelper.getLogger(EmailReport.class);
	
	static String username, receiver;
    static String password;
    static String server;
	
	public EmailReport() {

		ObjectReader.reader = new PropertyReader();
		
		username = ObjectReader.reader.getGmailUsername();
		password = ObjectReader.reader.getGmailPassword();
		receiver = ObjectReader.reader.getReceiverEmail();
		
		server = ObjectReader.reader.getServer();
	}
	
	
	public static void sendEmailReport(String className) throws IOException
	{
		
	    Properties props = new Properties();
	    props.put("mail.smtp.auth", true);
	    props.put("mail.smtp.starttls.enable", true);
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");

	    Session session = Session.getInstance(props, new javax.mail.Authenticator()
	    {
	    	protected PasswordAuthentication getPasswordAuthentication()
	    	{
	    		return new PasswordAuthentication(username, password);
	    	}
	    }
	    );
	    
	    try {

	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(username));
	        message.setRecipients(Message.RecipientType.TO,
	                InternetAddress.parse(receiver));
	        LocalDateTime timenow = LocalDateTime.now();
	        message.setSubject(className + " - " + server.toUpperCase());
	        message.setText("Test Report");
	        
	        
	        MimeMultipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(className + " - " + DateTimeFormatter.ofPattern("dd/MM/yyy hh:mm:ss").format(timenow) + " - " + server,
            		"iso-8859-1", "html");
            multipart.addBodyPart(messageBodyPart);

            MimeBodyPart attachPart = new MimeBodyPart();
            File attachement = new File(System.getProperty("user.dir") + "\\test-output\\emailable-report.html");
            if (attachement.exists())
            {
                attachPart.attachFile(attachement);
                multipart.addBodyPart(attachPart);
            }
            else
            {
                System.out.println("ERROR READING THE FILE");
                throw new FileNotFoundException();
            }
            message.setContent(multipart);
	        
	        log.info("Sending Test Report...");

	        Transport.send(message);

	        log.info("Test Report Successfully sent via Email.");

	    }
	    catch (MessagingException e)
	    {
	        e.printStackTrace();
	    }
	}

	
	public static void sendExtentEmailReport(String className) throws IOException
	{
		
	   Properties props = new Properties();
	    props.put("mail.smtp.auth", true);
	    props.put("mail.smtp.starttls.enable", true);
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");

	    Session session = Session.getInstance(props, new javax.mail.Authenticator()
	    {
	    	protected PasswordAuthentication getPasswordAuthentication()
	    	{
	    		return new PasswordAuthentication(username, password);
	    	}
	    }
	    );
	    
	    try {

	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(username));
	        message.setRecipients(Message.RecipientType.TO,
	                InternetAddress.parse(receiver));
	        LocalDateTime timenow = LocalDateTime.now();
	        message.setSubject(className + " - " + server.toUpperCase());
	        message.setText("Test Report");
	        
	        
	        MimeMultipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(className + " - " + DateTimeFormatter.ofPattern("dd/MM/yyy hh:mm:ss").format(timenow) + " - " + server,
            		"iso-8859-1", "html");
            multipart.addBodyPart(messageBodyPart);

            MimeBodyPart attachPart = new MimeBodyPart();
            
            String reportPath = FileUtilities.getLatestFilefromDir(
            		ResourcePath.getResourcePath("src/main/resources/reports/")).toString();
            
            
            File attachement = new File(reportPath);
            if (attachement.exists())
            {
                attachPart.attachFile(attachement);
                multipart.addBodyPart(attachPart);
            }
            else
            {
                log.info("ERROR READING THE FILE for Email Attachment.");
                throw new FileNotFoundException();
            }
            message.setContent(multipart);
	        
	        log.info("Sending Test Report...");

	        Transport.send(message);

	        log.info("Test Report Successfully sent via Email.");

	    }
	    catch (MessagingException e)
	    {
	        e.printStackTrace();
	    }
	}
}
