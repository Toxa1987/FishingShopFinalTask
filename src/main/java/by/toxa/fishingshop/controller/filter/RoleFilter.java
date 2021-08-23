package by.toxa.fishingshop.controller.filter;

import by.toxa.fishingshop.controller.command.*;
import by.toxa.fishingshop.model.entity.Role;
import by.toxa.fishingshop.model.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.toxa.fishingshop.controller.command.CommandType.DEFAULT;
import static by.toxa.fishingshop.controller.command.CommandType.valueOf;
import static by.toxa.fishingshop.controller.command.RequestParameter.*;

@WebFilter(urlPatterns = "/ApiController", dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST})
public class RoleFilter implements Filter {
    private FilterSecurityProvider provider = FilterSecurityProvider.getInstance();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        if (user == null) {
            user = new User.UserBuilder()
                    .setRole(Role.GUEST)
                    .build();
            session.setAttribute(SessionAttribute.USER, user);
            session.setAttribute(SessionAttribute.NOT_AUTHENTICATED, true);
            session.setAttribute(SessionAttribute.AUTHENTICATED, false);
        }
        CommandType commandType;
        try {
            commandType = valueOf(request.getParameter(COMMAND).toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = DEFAULT;
        }
        if (!provider.isUserCan(user, commandType)) {
            servletRequest.getRequestDispatcher(PagePath.INDEX).forward(servletRequest, servletResponse);
        }

        if (filterChain != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

}
