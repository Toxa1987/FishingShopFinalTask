package by.toxa.fishingshop._main;

import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.model.entity.User;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ServiceException, IOException {
   User user = new User.UserBuilder()
           .setLogin("_wertyal")
           .setPassword("12345")
           .build();
   new MainService().send(user);
        System.out.println(user);
    }
}