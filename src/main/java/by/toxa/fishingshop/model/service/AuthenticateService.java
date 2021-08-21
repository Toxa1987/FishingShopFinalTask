package by.toxa.fishingshop.model.service;

import by.toxa.fishingshop.model.entity.User;
import by.toxa.fishingshop.exception.ServiceException;

import java.util.Optional;

public interface AuthenticateService {
    Optional<User> authenticate(String login, String password) throws ServiceException;
}
