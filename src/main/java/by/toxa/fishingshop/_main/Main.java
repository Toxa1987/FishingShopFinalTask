package by.toxa.fishingshop._main;

import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.model.entity.User;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ServiceException, IOException {
   User user = new User.UserBuilder()
           .setLogin("wer")
           .setPassword("12_33")
           .setEmail("wertyal.87@mail.ru")
           .build();
   new MainService().send(user);
        System.out.println(user);
    }
}