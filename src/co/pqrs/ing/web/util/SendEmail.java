package co.pqrs.ing.web.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author Alejandro Serna - Email: alejandro.serna3@gmail.com
 * @author Nilton Velez - Email: nilton.velez@udea.edu.co
 * @author Camilo Lopez - Email: lopcamilo@gmail.com 
 * Clase creada para mandar los Email a los respectivos usuarios
 */
public class SendEmail {
    /**
     * @param to: 
     * @param subject:
     * @param body:
     * @throws UnsupportedEncodingException
     */
    public static void sendMail(String to, String subject, String body) throws UnsupportedEncodingException{
        Properties props = new Properties();
        ResourceBundle resource = ResourceBundle.getBundle("mail");
        props.put("mail.smtp.host", resource.getString("mail.smtp.host"));
        props.put("mail.smtp.auth", resource.getString("mail.smtp.auth"));
       // props.put("mail.debug", "true");
        props.put("mail.smtp.port",resource.getString("mail.smtp.port"));
        props.put("mail.smtp.socketFactory.port", resource.getString("mail.smtp.socketFactory.port"));
       // props.put("mail.smtp.socketFactory.class",
        //        "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.starttls.enable", resource.getString("mail.smtp.starttls.enable"));
        props.put("mail.transport.protocol", resource.getString("mail.transport.protocol"));
       // props.put("mail.smtp.ssl.trust", "*");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(resource.getString("mail.account.user"),resource.getString("mail.account.password"));
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(resource.getString("mail.account.user"), resource.getString("mail.account.user")));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);
            message.setContent(body,"text/html");

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
	
}
