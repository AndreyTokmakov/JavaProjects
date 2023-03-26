package smtp.tests;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class HtmlEmailSender {

 	public static void main(String[] args) throws IOException {
 		String html_message = "<i>Greetings!</i><br>";
        html_message += "<b>Wish you a nice day!</b><br>";
        html_message += "<font color=red>Duke</font>";

        final String username = "andtokm@yandex.ru";
        final String password = "ziudjagaggggggggg";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.yandex.ru");        
        props.put("mail.debug", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", 465);
        props.put("mail.smtp.socketFactory.port", 465);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        Session session = Session.getInstance(props, new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                                        return new PasswordAuthentication(username, password);
             }
        });

        try {
            Message message = new MimeMessage(session);
            InternetAddress from = new InternetAddress("andtokm@yandex.ru");
            InternetAddress to   = new InternetAddress("andtokmtest2@yandex.ru");

            // String message_body = FileUtils.readFileToString(new File("C:\\Temp\\MAIL_MESSAGES\\Message.txt"), "UTF-8");
            String message_body = html_message;

            message.setFrom(from);
            message.setRecipient(Message.RecipientType.TO, to);
            message.setSubject("Testing Yandex SMTP messaging");
            message.setContent(message_body, "text/html");
            // message.setText(message_text);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}