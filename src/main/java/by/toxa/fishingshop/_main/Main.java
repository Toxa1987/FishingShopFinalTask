package by.toxa.fishingshop._main;

import by.toxa.fishingshop.command.CommandType;
import by.toxa.fishingshop.connection.CustomConnectionPool;
import by.toxa.fishingshop.controller.filter.FilterSecurityProvider;
import by.toxa.fishingshop.entity.Product;
import by.toxa.fishingshop.entity.Role;
import by.toxa.fishingshop.entity.User;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.repository.impl.FindAllProductsSpecification;
import by.toxa.fishingshop.service.impl.ProductService;

import java.io.IOException;
import java.sql.Connection;
import java.util.*;

import static by.toxa.fishingshop.command.CommandType.*;

public class Main {

    public static void main(String[] args) throws ServiceException, IOException {
       ProductService service =  ProductService.getInstance();
      List<Product> list = service.query(new FindAllProductsSpecification());
      Product product =list.get(0);
      String s =Base64.getEncoder().encodeToString(product.getImage().readAllBytes());
        System.out.println(s);
        System.out.println(Arrays.toString(Base64.getDecoder().decode(s)));
        CustomConnectionPool.getInstance().destroyPool();

    }
}