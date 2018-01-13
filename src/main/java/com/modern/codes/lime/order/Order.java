package com.modern.codes.lime.order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

@Service
public class Order {

    @Autowired
    public Order() {
    }


    public static String ConstructOrderMsg(String SupplierName, String ResourceName, Integer Amount){
       return "Dear " + SupplierName + ", " + "\n\n We would like to order " + Amount.toString() + " units of " + ResourceName + " for our laboratory. \n\n";

    }
    public static void SendEmail(String to, String subject, String content, String attachment){
        final String username = "lime.lab.application@gmail.com";
        final String password = "lime1234";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("lime.lab.application@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);

            Multipart multipart = new MimeMultipart();

            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText(content);

            MimeBodyPart attachmentBodyPart= new MimeBodyPart();
            DataSource source = new FileDataSource(attachment);
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName(source.getName());

            multipart.addBodyPart(textBodyPart);
            multipart.addBodyPart(attachmentBodyPart);

            message.setContent(multipart);
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    public static void SendEmail(String to, String subject, String content){
        final String username = "lime.lab.application@gmail.com";
        final String password = "lime1234";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("lime.lab.application@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}


