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

import com.modern.codes.lime.model.Resource;
import com.modern.codes.lime.pojo.ResourcePOJO;

@Service
public class Order {

    @Autowired
    public Order() {
    }


    public static String ConstructOrderMsg(final String supplierName, final Map<Resource, Integer> map){
        final StringBuilder message = new StringBuilder("Dear " + supplierName + ", " + "\n\n We would like to order: \n");
        map.forEach((key, value) -> message.append(key.getName())
                                           .append(" : ")
                                           .append(value)
                                           .append(' ')
                                           .append(key
                                                    .getUnit())
                                           .append('\n'));

        message.append("Regards, \nLIME team.\n");
        return message.toString();

    }
    public static void SendEmail(final String to, final String subject, final String content, final String attachment){
        final Session session = getSession();
        try {

            final Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("lime.lab.application@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);

            final Multipart multipart = new MimeMultipart();

            final MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText(content);

            final MimeBodyPart attachmentBodyPart= new MimeBodyPart();
            final DataSource source = new FileDataSource(attachment);
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName(source.getName());

            multipart.addBodyPart(textBodyPart);
            multipart.addBodyPart(attachmentBodyPart);

            message.setContent(multipart);
            Transport.send(message);


        } catch (final MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    public static void SendEmail(final String to, final String subject, final String content){
        final Session session = getSession();
        try {

            final Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("lime.lab.application@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);

        } catch (final MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private static Properties getProperties() {
        final Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        return props;
    }
    private static Session getSession(){
        final Properties props = getProperties();
        final String username = "lime.lab.application@gmail.com";
        final String password = "lime1234";
        return Session.getInstance(props,
                                   new javax.mail.Authenticator() {
                                       protected PasswordAuthentication getPasswordAuthentication() {
                                           return new PasswordAuthentication(username, password);
                                       }
                                   });
    }
}


