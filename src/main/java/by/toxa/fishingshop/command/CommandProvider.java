package by.toxa.fishingshop.command;

import by.toxa.fishingshop.command.impl.*;

import java.util.EnumMap;

import static by.toxa.fishingshop.command.CommandType.*;

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
        commands.put(LOG_OUT_COMMAND,new LogOutCommand());
        commands.put(GO_TO_EDIT_PRODUCT_PAGE_COMMAND,new GoToEditProductPageCommand());
        commands.put(EDIT_NEW_PRODUCT_COMMAND,new EditNewProductCommand());
        commands.put(DEFAULT, new DefaultCommand());
    }

    public static CommandProvider getInstance() {
        if (instance == null) {
            instance = new CommandProvider();
        }
        return instance;
    }

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

