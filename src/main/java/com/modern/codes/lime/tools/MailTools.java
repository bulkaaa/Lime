package com.modern.codes.lime.tools;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

public class MailTools {
    public static void SendEmail(final String to, final String subject, final String content, final byte[] attachment)
            throws MessagingException {
        final Session session = getSession();

        final Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("lime.lab.application@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);

        final Multipart multipart = new MimeMultipart();

        final MimeBodyPart textBodyPart = new MimeBodyPart();
        textBodyPart.setText(content);

        final MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        final ByteArrayDataSource rawData = new ByteArrayDataSource(attachment, "image/jpeg");
        attachmentBodyPart.setDataHandler(new DataHandler(rawData));
        attachmentBodyPart.setFileName("report.jpg");

        multipart.addBodyPart(textBodyPart);
        multipart.addBodyPart(attachmentBodyPart);

        message.setContent(multipart);
        Transport.send(message);

    }

    public static void SendEmail(final String to, final String subject, final String content) throws
                                                                                              MessagingException {
        final Session session = getSession();
        final Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("lime.lab.application@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(content);

        Transport.send(message);
    }

    private static Properties getProperties() {
        final Properties props = new Properties();
        props.put("mail.smtp.auth", MAIL_SMTP_AUTH);
        props.put("mail.smtp.starttls.enable", MAIL_SMTP_STARTTLS_ENDABLE);
        props.put("mail.smtp.host", MAIL_SMTP_HOST);
        props.put("mail.smtp.port", MAIL_SMTP_PORT);
        return props;
    }

    private static Session getSession() {
        final Properties props = getProperties();
        return Session.getInstance(props, new javax.mail.Authenticator() {
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
