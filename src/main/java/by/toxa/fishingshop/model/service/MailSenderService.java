package by.toxa.fishingshop.model.service;

import by.toxa.fishingshop.exception.ServiceException;

import java.util.List;

public interface MailSenderService {
    void send(String email,String subject,String message) throws ServiceException;
    void sendForSeveral(List<String> emailList, String subject, String message) throws ServiceException;

}
