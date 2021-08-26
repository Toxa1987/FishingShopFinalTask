package by.toxa.fishingshop.controller.filter;

import by.toxa.fishingshop.controller.command.*;
import by.toxa.fishingshop.model.entity.Role;
import by.toxa.fishingshop.model.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.toxa.fishingshop.controller.command.CommandType.*;
import static by.toxa.fishingshop.controller.command.RequestParameter.*;

@WebFilter(urlPatterns = "/ApiController")
public class RoleFilter implements Filter {
    private FilterSecurityProvider provider = FilterSecurityProvider.getInstance();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
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
            if (user.getRole() == Role.GUEST || user.getRole() == Role.USER) {
                response.sendRedirect(PagePath.INDEX);
            }
            if (user.getRole() == Role.MANAGER) {
                response.sendRedirect(PagePath.GO_TO_MANAGER_PAGE);
            }
            if (user.getRole() == Role.ADMIN) {
                response.sendRedirect(PagePath.GO_TO_ADMIN_PAGE);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

}
