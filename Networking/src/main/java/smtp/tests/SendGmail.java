package smtp.tests;

import jakarta.mail.*;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendGmail
{
    public static void main(String[] args)
    {
        final String username = "andtokmtest1@gmail.com";
        final String password = "aDg4M9L9QNLqQGLrrUG87j_VCP9c";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");


        javax.mail.Session session = javax.mail.Session.getInstance(prop, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }
        });

        try {
            javax.mail.Message message = new MimeMessage(session);
            javax.mail.internet.InternetAddress fromAdd = new javax.mail.internet.InternetAddress("andtokmtest@yandex.ru");
            javax.mail.internet.InternetAddress to   = new InternetAddress("andtokmtest2@yandex.ru");

            // String message_body = FileUtils.readFileToString(new File("C:\\Temp\\MAIL_MESSAGES\\Message.txt"), "UTF-8");
            String html_message = "<i>Greetings!</i><br>";
            html_message += "<b>Wish you a nice day!</b><br>";
            html_message += "<font color=red>Duke</font>";
            String message_body = html_message;

            message.setFrom(fromAdd);
            message.setRecipient(javax.mail.Message.RecipientType.TO, to);
            message.setSubject("Testing Yandex SMTP messaging");
            message.setContent(message_body, "text/html");
            // message.setText(message_text);
            javax.mail.Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
