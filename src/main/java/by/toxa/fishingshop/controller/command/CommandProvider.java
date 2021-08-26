package by.toxa.fishingshop.controller.command;

import by.toxa.fishingshop.controller.command.impl.*;
import by.toxa.fishingshop.controller.command.impl.auth.ActivateCommand;
import by.toxa.fishingshop.controller.command.impl.auth.LogOutCommand;
import by.toxa.fishingshop.controller.command.impl.auth.LoginCommand;
import by.toxa.fishingshop.controller.command.impl.auth.SignUpCommand;
import by.toxa.fishingshop.controller.command.impl.go.*;

import java.util.EnumMap;

import static by.toxa.fishingshop.controller.command.CommandType.*;

/**
 * Command provider provide command {@link Command} by CommandType {@link CommandType}.
 *
 * @author Anton Pysk
 */
public class CommandProvider {
    private static CommandProvider instance;
    private final EnumMap<CommandType, Command> commands = new EnumMap(CommandType.class);

    private CommandProvider() {
        commands.put(START_PAGE_COMMAND, new StartPageCommand());
        commands.put(GO_TO_USERS_PAGE_COMMAND, new GoToUsersPageCommand());
        commands.put(GO_TO_LOGIN_PAGE_COMMAND, new GoToLoginPageCommand());
        commands.put(LOGIN_COMMAND, new LoginCommand());
        commands.put(GO_TO_SIGN_UP_PAGE_COMMAND, new GoToSignUpPageCommand());
        commands.put(SIGN_UP_COMMAND, new SignUpCommand());
        commands.put(GO_TO_ACTIVATION_PAGE_COMMAND, new GoToActivationPageCommand());
        commands.put(ACTIVATE_COMMAND, new ActivateCommand());
        commands.put(LOG_OUT_COMMAND, new LogOutCommand());
        commands.put(GO_TO_EDIT_PRODUCT_PAGE_COMMAND, new GoToEditProductPageCommand());
        commands.put(EDIT_NEW_PRODUCT_COMMAND, new EditNewProductCommand());
        commands.put(GO_TO_PRODUCT_PAGE, new GoToProductPageCommand());
        commands.put(SET_TO_CART_COMMAND, new SetToCartCommand());
        commands.put(CLEAR_CART_COMMAND, new ClearCartCommand());
        commands.put(PURCHASE_COMMAND, new PurchaseCommand());
        commands.put(GO_TO_MANAGER_PAGE_COMMAND, new GoToManagerPageCommand());
        commands.put(GET_PRODUCTS_BY_TYPE_COMMAND, new GetProductsByTypeCommand());
        commands.put(GO_TO_USER_ORDERS_PAGE_COMMAND, new GoToUserOrdersPageCommand());
        commands.put(CHANGE_ORDER_STATUS_COMMAND, new ChangeOrderStatusCommand());
        commands.put(GET_ORDER_DATA_COMMAND, new GetOrderDataCommand());
        commands.put(GO_TO_SEARCH_PRODUCT_BY_VENDOR_PAGE_COMMAND, new GoToSearchProductByVendorPageCommand());
        commands.put(GO_TO_SEARCH_PRODUCT_BY_NAME_PAGE_COMMAND, new GoToSearchProductByNamePageCommand());
        commands.put(SEARCH_PRODUCT_BY_PRODUCT_NAME_COMMAND, new SearchProductByProductNameCommand());
        commands.put(SEARCH_PRODUCT_BY_VENDOR_COMMAND, new SearchProductByVendorCommand());
        commands.put(GET_PRODUCT_TO_UPDATE_COMMAND, new GetProductToUpdateCommand());
        commands.put(UPDATE_PRODUCT_COMMAND, new UpdateProductCommand());
        commands.put(FIND_ORDERS_BY_STATUS_COMMAND, new FindOrdersByStatusCommand());
        commands.put(GO_TO_EDIT_MANAGER_PAGE_COMMAND, new GoToEditManagerPageCommand());
        commands.put(EDIT_MANAGER_COMMAND, new EditManagerCommand());
        commands.put(GO_TO_ADMIN_PAGE_COMMAND, new GoToAdminPageCommand());
        commands.put(CHANGE_USER_STATUS_COMMAND, new ChangeUserStatusCommand());
        commands.put(GO_TO_SEARCH_USER_BY_LOGIN_COMMAND, new GoToSearchUserByLoginCommand());
        commands.put(SEARCH_USER_BY_LOGIN_COMMAND, new SearchUserByLoginCommand());
        commands.put(CHANGE_LOCALE_COMMAND, new ChangeLocaleCommand());
        commands.put(DEFAULT, new DefaultCommand());
    }

    public static CommandProvider getInstance() {
        if (instance == null) {
            instance = new CommandProvider();
        }
        return instance;
    }

    /**
     * Gets command.
     *
     * @param commandName the command name
     * @return the command {@link Command} from EnumMap where key commandType{@link CommandType}.
     */
    public Command getCommand(String commandName) {
        if (commandName == null) {
            return commands.get(DEFAULT);
        }
        CommandType commandType;
        try {
            commandType = valueOf(commandName.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = DEFAULT;
        }
        return commands.get(commandType);
    }
}

