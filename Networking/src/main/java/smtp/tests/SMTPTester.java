/**
 * 
 */
package smtp.tests;

import com.sun.mail.smtp.SMTPTransport;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/** SMTPTester class : **/
public class SMTPTester {
	/** **/
	public SMTPTester() {
		// TODO Auto-generated constructor stub
	}
	
	public static void MailTest()
	{
		final String username = "andtokm@mail.ru";
		final String password = "ziudjaga";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "false");
		//props.put("mail.smtp.starttls.enable", "false");
		//props.put("mail.smtp.host", "tech.linx.kdp");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("andtokm@mail.ru"));
			//message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("andrey.tokmakov@kaspersky.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("andtokm@yandex.ru"));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler,"+ "\n\n No spam to my email, please!");
			Transport.send(message);
			System.out.println("Done");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
    /**
     * Send email using GMail SMTP server.
     *
     * @param username GMail username
     * @param password GMail password
     * @param recipientEmail TO recipient
     * @param ccEmail CC recipient. Can be empty if there is no CC recipient
     * @param title title of the message
     * @param message message to be sent
     * @throws AddressException if the email address parse failed
     * @throws MessagingException if the connection is dead or not in the connected state or if the message is not a MimeMessage
     */
    public static void GmailSend(final String username,
                                 final String password,
                                 String recipientEmail,
                                 String ccEmail,
                                 String title,
                                 String message) throws AddressException, MessagingException {
        //Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtps.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtps.auth", "true");

        /*
        If set to false, the QUIT command is sent and the connection is immediately closed. If set 
        to true (the default), causes the transport to wait for the response to the QUIT command.

        ref :   http://java.sun.com/products/javamail/javadocs/com/sun/mail/smtp/package-summary.html
                http://forum.java.sun.com/thread.jspa?threadID=5205249
                smtpsend.java - demo program from javamail
        */
        props.put("mail.smtps.quitwait", "false");

        Session session = Session.getInstance(props, null);

        // -- Create a new message --
        final MimeMessage msg = new MimeMessage(session);

        // -- Set the FROM and TO fields --
        msg.setFrom(new InternetAddress(username + "@gmail.com"));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));

        if (ccEmail.length() > 0) {
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmail, false));
        }

        msg.setSubject(title);
        msg.setText(message, "utf-8");
        msg.setSentDate(new Date());

        SMTPTransport transport = (SMTPTransport)session.getTransport("smtps");
        transport.connect("smtp.gmail.com", username, password);
        transport.sendMessage(msg, msg.getAllRecipients());      
        transport.close();
    }
    
    public static void SendEmail_MailRu()
    {
     	final String username = "andtokm@mail.ru";
        final String password = "123!@#QWEqwe";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.mail.ru");        
        props.put("mail.debug", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", 465);
        props.put("mail.smtp.socketFactory.port", 465);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                    	  protected PasswordAuthentication getPasswordAuthentication() {
                    			return new PasswordAuthentication(username, password);
             }
        });

        try {
            Message message = new MimeMessage(session);
            InternetAddress from = new InternetAddress("andtokm@mail.ru");
            InternetAddress to   = new InternetAddress("andtokm@yandex.ru");
            
            message.setFrom(from);
            message.setRecipient(Message.RecipientType.TO, to);
            message.setSubject("Testing MailRU SMTP TLS Messaging");
            message.setText("Dear Mail Crawler," + "\n\n Please do not spam my email!");
            
            Transport.send(message);
            
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    public static void SendEmail_Yandex()
    {
     	final String username = "andtokm@yandex.ru";
        final String password = "ziudjagaggggggggg";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.yandex.ru");        
        props.put("mail.debug", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", 465);
        //props.put("mail.smtp.socketFactory.port", 465);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        //props.put("mail.smtp.socketFactory.fallback", "false");
        
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                    	  protected PasswordAuthentication getPasswordAuthentication() {
                    			return new PasswordAuthentication(username, password);
             }
        });

        try {
            Message message = new MimeMessage(session);
            InternetAddress from = new InternetAddress("andtokm@yandex.ru");
            InternetAddress to   = new InternetAddress("andtokm@yandex.ru");
            
            String htmlMsg = "<h2>Java Mail Example</h2><p>hi there!</p>";
            
            message.setFrom(from);
            message.setContent(htmlMsg, "text/html; charset=utf-8");
            message.setRecipient(Message.RecipientType.TO, to);
            message.setSubject("Testing Yandex SMTP TLS Messaging");
            
            //message.setText("Dear Mail Crawler," + "\n\n Please do not spam my email!");
            
            Transport.send(message);
            
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
	
	/**
	 * @param args
	 * @throws MessagingException 
	 * @throws AddressException 
	 */
	public static void main(String[] args) throws AddressException, MessagingException {
		//MailTest();
		GmailSend("andtokm",
                "ziudjagaggggggggg",
                "andtokm@yandex.ru",
                "",
                "TEST_TITLE",
                "TEST_MESSAGE");
		
		//SendEmail_MailRu();
		// SendEmail_Yandex();
	}


	
}
