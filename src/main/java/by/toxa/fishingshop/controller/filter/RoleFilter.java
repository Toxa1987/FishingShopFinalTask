package by.toxa.fishingshop.controller.filter;

import by.toxa.fishingshop.command.SessionAttribute;
import by.toxa.fishingshop.entity.Role;
import by.toxa.fishingshop.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/ApiController", dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST})
public class RoleFilter implements Filter {
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
            session.setAttribute(SessionAttribute.NOT_AUTHENTICATED,true);
        }
        if (filterChain != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

}
