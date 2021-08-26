package by.toxa.fishingshop.model.service.impl;

import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.model.service.MailSenderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class MailSenderServiceImpl implements MailSenderService {
    private final static Logger logger = LogManager.getLogger();
    private static Session session;
    private static MailSenderServiceImpl instance;
    private static String emailSender;

    private MailSenderServiceImpl() {
        Properties properties = new Properties();
        try (InputStream inputStream = MailSenderServiceImpl.class.getClassLoader().getResourceAsStream("mail/mail.properties")) {
            properties.load(inputStream);
            emailSender = properties.getProperty("mail.username");
            session = Session.getDefaultInstance(properties, new MailAuthenticator());
        } catch (IOException e) {
            logger.error("Can not load property data to mail sender ", e);
            throw new RuntimeException("Can't load properties file: ", e);
        }
    }

    public static MailSenderServiceImpl getInstance() {
        if (instance == null) {
            instance = new MailSenderServiceImpl();
        }
        return instance;
    }

    @Override
    public void send(String email, String subject, String message) throws ServiceException {
        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(emailSender));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            mimeMessage.setSubject(subject);
            mimeMessage.setContent(message, "text/html");
            Transport.send(mimeMessage);
            logger.info("email send successfully to " + email);
        } catch (MessagingException e) {
            logger.error("Fail while mail was sending.", e);
            throw new ServiceException("Fail while mail was sending.", e);
        }
    }

}
