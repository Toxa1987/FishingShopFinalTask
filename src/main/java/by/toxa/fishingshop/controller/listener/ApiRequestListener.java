package by.toxa.fishingshop.controller.listener;

import by.toxa.fishingshop.controller.command.PagePath;
import by.toxa.fishingshop.controller.command.SessionAttribute;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionListener;
import java.net.http.HttpRequest;

@WebListener()
public class ApiRequestListener implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        String queryString = request.getQueryString();
        if (queryString != null) {
            if (queryString.compareToIgnoreCase("command=change_locale_command") != 0) {
                String prevRequest = PagePath.CONTROLLER_URL + queryString;
                HttpSession session = request.getSession(true);
                session.setAttribute(SessionAttribute.PREVIOUS_REQUEST, prevRequest);
            }
        }
    }
}
