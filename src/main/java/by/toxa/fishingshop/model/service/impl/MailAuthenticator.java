package by.toxa.fishingshop.model.service.impl;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

class MailAuthenticator extends Authenticator {
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(
                "wertyal.87@mail.ru",
                "wertyalwertyal");
    }
}
