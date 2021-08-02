package by.toxa.fishingshop.controller.filter;

import by.toxa.fishingshop.command.CommandType;
import by.toxa.fishingshop.entity.Role;
import by.toxa.fishingshop.entity.User;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Optional;

import static by.toxa.fishingshop.command.CommandType.*;

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
                GO_TO_SIGN_UP_PAGE_COMMAND};
        CommandType[] commandTypesOfUser = {
                DEFAULT,
                START_PAGE_COMMAND,
                LOG_OUT_COMMAND};
        CommandType[] commandTypesOfManager = {
                DEFAULT,
                START_PAGE_COMMAND,
                LOG_OUT_COMMAND,
                GO_TO_EDIT_PRODUCT_PAGE_COMMAND,
                EDIT_NEW_PRODUCT_COMMAND};
        CommandType[] commandTypesOfAdmin = {
                DEFAULT,
                START_PAGE_COMMAND,
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
