package by.toxa.fishingshop._main;

import by.toxa.fishingshop.model.entity.User;
import by.toxa.fishingshop.model.validator.UserValidator;

public class MainService {
    public void send(User user){
        if(!UserValidator.isValidLogin(user.getLogin())){
            user.setLogin(null);
        }
        if(!UserValidator.isValidPassword(user.getPassword())){
            user.setPassword(null);
        }
        if(!UserValidator.isValidEmail(user.getEmail())){
            user.setEmail(null);
        }
    }
}
