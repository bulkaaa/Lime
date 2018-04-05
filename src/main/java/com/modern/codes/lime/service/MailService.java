package com.modern.codes.lime.service;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
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
import javax.mail.util.ByteArrayDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.modern.codes.lime.pojo.ResourcePOJO;

/**
 * The type Mail service.
 */
@Service
public class MailService implements IMailService {

    @Override
    public String prepareWelcomeEmailBody(final String username, final String password, final String name,
                                          final String surname) {
        try {
            final URL url = Resources.getResource("WelcomeEmail.html");
            final String email = Resources.toString(url, Charsets.UTF_8);
            email.replace("name_input", name);
            email.replace("surname_input", name);
            email.replace("username_input", name);
            email.replace("password_input", name);
            return email;
        } catch (final IOException e) {
            LOG.error("Error parsing Welcome Email.");
            return null;
        }
    }

    /**
     * Send email.
     *
     * @param to         the to
     * @param subject    the subject
     * @param content    the content
     * @param attachment the attachment
     * @throws MessagingException the messaging exception
     */
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

    /**
     * Send email.
     *
     * @param to      the to
     * @param subject the subject
     * @param content the content
     * @throws MessagingException the messaging exception
     */
    public static void SendEmail(final String to, final String subject, final String content)
            throws MessagingException {
        final Session session = getSession();
        final Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("lime.lab.application@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(content);

        Transport.send(message);
    }

    /**
     * Construct order msg string.
     *
     * @param supplierName the supplier name
     * @param map          the map
     * @return the string
     */
    public static String ConstructOrderMsg(final String supplierName, final Map<ResourcePOJO, Integer> map) {
        final StringBuilder message =
                new StringBuilder("Dear " + supplierName + ", " + "\n\nWe would like to order: \n");
        map.forEach((key, value) -> message.append(key.getName())
                                           .append(" : ")
                                           .append(value)
                                           .append(' ')
                                           .append(key.getUnit())
                                           .append('\n'));

        message.append("Regards, \nLIME team.\n");
        return message.toString();
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

    private static final Logger LOG = LoggerFactory.getLogger(MailService.class);

    private static final String MAIL_SMTP_AUTH = "true";
    private static final String MAIL_SMTP_STARTTLS_ENDABLE = "true";
    private static final String MAIL_SMTP_HOST = "smtp.gmail.com";
    private static final String MAIL_SMTP_PORT = "587";
    private static final String USERNAME = "lime.lab.application@gmail.com";
    private static final String PASSWORD = "lime1234";

}
