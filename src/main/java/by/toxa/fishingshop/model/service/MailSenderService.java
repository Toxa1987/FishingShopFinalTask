package by.toxa.fishingshop.model.service;

import by.toxa.fishingshop.exception.ServiceException;

import java.util.List;

/**
 * The interface MailSenderService.
 * <p>
 * Include method to send a message to user by email
 *
 * @author Anton Pysk
 */

public interface MailSenderService {
    /**
     *
     * @param email {@link String}
     * @param subject {@link String}
     * @param message {@link String}
     * @throws ServiceException the service exception
     */
    void send(String email,String subject,String message) throws ServiceException;
  }
