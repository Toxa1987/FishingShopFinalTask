package by.toxa.fishingshop.command;

import by.toxa.fishingshop.command.impl.DefaultCommand;
import by.toxa.fishingshop.command.impl.GoToStartPageCommand;
import by.toxa.fishingshop.command.impl.GoToUsersPageCommand;

import java.util.EnumMap;

import static by.toxa.fishingshop.command.CommandType.*;

public class CommandProvider {
    private static CommandProvider instance;
    private final EnumMap<CommandType, Command> commands = new EnumMap(CommandType.class);

    private CommandProvider() {
        commands.put(GO_TO_START_PAGE_COMMAND,new GoToStartPageCommand());
        commands.put(GO_TO_USERS_PAGE_COMMAND,new GoToUsersPageCommand());
        commands.put(DEFAULT,new DefaultCommand());
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

