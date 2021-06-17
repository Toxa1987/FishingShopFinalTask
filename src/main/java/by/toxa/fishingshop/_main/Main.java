package by.toxa.fishingshop._main;

import by.toxa.fishingshop.connection.CustomConnectionPool;
import by.toxa.fishingshop.entity.Role;
import by.toxa.fishingshop.entity.Status;
import by.toxa.fishingshop.entity.User;
import by.toxa.fishingshop.repository.UserRepository;
import by.toxa.fishingshop.repository.impl.FindById;
import by.toxa.fishingshop.repository.impl.FindByLogin;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        List<User> userList = userRepository.query(new FindById(5));
        userList.forEach(System.out::println);
        CustomConnectionPool.getInstance().destroyPool();
       }
}
