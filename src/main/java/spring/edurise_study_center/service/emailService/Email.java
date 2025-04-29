package spring.edurise_study_center.service.emailService;

import com.sun.mail.util.MailSSLSocketFactory;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Properties;

public class Email {
    static Message message;

    static {

        System.out.println("keldiiiiiiiiii\n\n\n\n\n\n\n");
        String password = "bryw ycyu ddxt xevm";
        String username = "xakimovilyosbobo@gmail.com";
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(properties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        Message m = new MimeMessage(session);
        try {
            m.setSubject("application enterance code ");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        try {
            m.setFrom(new InternetAddress(username));
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        message = m;
    }

    public static String sendCode(String to) {

        SecureRandom secureRandom = new SecureRandom();
        String code = String.valueOf(secureRandom.nextInt(1000, 9999));
        try {
            message.setText("code : " + code);
            message.setSubject("Aplication entry code");
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException();
        }
        System.out.println("successful send");
        return code;
    }


}
