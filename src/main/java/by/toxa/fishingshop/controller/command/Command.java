package by.toxa.fishingshop.controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Anton Pysk
 *
 * Represents a supplier of router.
 */
@FunctionalInterface
public interface Command {
    /**
     * @param request instance of {@link HttpServletRequest} from controller.
     * @return {@link Router} instance.
     */
    Router execute(HttpServletRequest request);
}
