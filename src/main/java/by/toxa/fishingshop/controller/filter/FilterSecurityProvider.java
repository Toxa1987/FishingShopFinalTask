package by.toxa.fishingshop.controller.filter;

import by.toxa.fishingshop.controller.command.CommandType;
import by.toxa.fishingshop.model.entity.Role;
import by.toxa.fishingshop.model.entity.User;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Optional;

import static by.toxa.fishingshop.controller.command.CommandType.*;

public class FilterSecurityProvider {
    private static FilterSecurityProvider instance;
    private EnumMap<Role, CommandType[]> roleEnumMap = new EnumMap<>(Role.class);

    private FilterSecurityProvider() {
        CommandType[] commandTypesOfGuest = {
                DEFAULT,
                START_PAGE_COMMAND,
                GO_TO_ACTIVATION_PAGE_COMMAND,
                ACTIVATE_COMMAND,
                LOGIN_COMMAND,
                SIGN_UP_COMMAND,
                GO_TO_LOGIN_PAGE_COMMAND,
                GO_TO_SIGN_UP_PAGE_COMMAND,
                GO_TO_SEARCH_PRODUCT_BY_NAME_PAGE_COMMAND,
                SEARCH_PRODUCT_BY_PRODUCT_NAME_COMMAND,
                GO_TO_PRODUCT_PAGE,
                CHANGE_LOCALE_COMMAND,
                GET_PRODUCTS_BY_TYPE_COMMAND};
        CommandType[] commandTypesOfUser = {
                DEFAULT,
                START_PAGE_COMMAND,
                GO_TO_PRODUCT_PAGE,
                LOG_OUT_COMMAND,
                SET_TO_CART_COMMAND,
                CLEAR_CART_COMMAND,
                PURCHASE_COMMAND,
                GO_TO_SEARCH_PRODUCT_BY_NAME_PAGE_COMMAND,
                SEARCH_PRODUCT_BY_PRODUCT_NAME_COMMAND,
                GET_PRODUCTS_BY_TYPE_COMMAND,
                CHANGE_ORDER_STATUS_COMMAND,
                CHANGE_LOCALE_COMMAND,
                GO_TO_USER_ORDERS_PAGE_COMMAND,
                GET_ORDER_DATA_COMMAND};
        CommandType[] commandTypesOfManager = {
                DEFAULT,
                LOG_OUT_COMMAND,
                GO_TO_EDIT_PRODUCT_PAGE_COMMAND,
                CHANGE_ORDER_STATUS_COMMAND,
                EDIT_NEW_PRODUCT_COMMAND,
                GET_ORDER_DATA_COMMAND,
                GO_TO_SEARCH_PRODUCT_BY_VENDOR_PAGE_COMMAND,
                GO_TO_SEARCH_PRODUCT_BY_NAME_PAGE_COMMAND,
                SEARCH_PRODUCT_BY_PRODUCT_NAME_COMMAND,
                SEARCH_PRODUCT_BY_VENDOR_COMMAND,
                GET_PRODUCT_TO_UPDATE_COMMAND,
                UPDATE_PRODUCT_COMMAND,
                FIND_ORDERS_BY_STATUS_COMMAND,
                CHANGE_LOCALE_COMMAND,
                GO_TO_MANAGER_PAGE_COMMAND};
        CommandType[] commandTypesOfAdmin = {
                DEFAULT,
                GO_TO_EDIT_MANAGER_PAGE_COMMAND,
                EDIT_MANAGER_COMMAND,
                GO_TO_SEARCH_USER_BY_LOGIN_COMMAND,
                SEARCH_USER_BY_LOGIN_COMMAND,
                GO_TO_ADMIN_PAGE_COMMAND,
                CHANGE_USER_STATUS_COMMAND,
                CHANGE_LOCALE_COMMAND,
                LOG_OUT_COMMAND};

        roleEnumMap.put(Role.GUEST, commandTypesOfGuest);
        roleEnumMap.put(Role.USER, commandTypesOfUser);
        roleEnumMap.put(Role.MANAGER, commandTypesOfManager);
        roleEnumMap.put(Role.ADMIN, commandTypesOfAdmin);
    }

    public static FilterSecurityProvider getInstance() {
        if (instance == null) {
            instance = new FilterSecurityProvider();
        }
        return instance;
    }

    public boolean isUserCan(User user, CommandType commandType) {
        boolean flag = false;
        CommandType[] types = roleEnumMap.get(user.getRole());
        Optional<CommandType> optionalCommandType = Arrays.stream(types)
                .filter(x -> x.equals(commandType))
                .findFirst();
        if (optionalCommandType.isPresent()) {
            flag = true;
        }
        return flag;
    }
}
