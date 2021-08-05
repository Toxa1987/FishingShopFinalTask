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
import java.time.LocalDate;
import java.util.*;

import static by.toxa.fishingshop.command.CommandType.*;

public class Main {

    public static void main(String[] args) throws ServiceException, IOException {
        LocalDate date = LocalDate.now();
        System.out.println("FishingShop-"+date+"-"+System.currentTimeMillis());
    }
}