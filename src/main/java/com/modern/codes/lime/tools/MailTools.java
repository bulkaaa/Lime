package com.modern.codes.lime.tools;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailTools {
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
        props.put("mail.smtp.auth", MAIL_SMTP_AUTH);
        props.put("mail.smtp.starttls.enable", MAIL_SMTP_STARTTLS_ENDABLE);
        props.put("mail.smtp.host", MAIL_SMTP_HOST);
        props.put("mail.smtp.port", MAIL_SMTP_PORT);
        return props;
    }
    private static Session getSession(){
        final Properties props = getProperties();
        return Session.getInstance(props,
                                   new javax.mail.Authenticator() {
                                       protected PasswordAuthentication getPasswordAuthentication() {
                                           return new PasswordAuthentication(USERNAME, PASSWORD);
                                       }
                                   });
    }
    private static final String MAIL_SMTP_AUTH = "true";
    private static final String MAIL_SMTP_STARTTLS_ENDABLE = "true";
    private static final String MAIL_SMTP_HOST = "smtp.gmail.com";
    private static final String MAIL_SMTP_PORT = "587";
    private static final String USERNAME = "lime.lab.application@gmail.com";
    private static final String PASSWORD = "lime1234";

}
