package com.user;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;

public class UserContext implements ServletContextListener {
    //ArrayList< UserInfo > users = new ArrayList< UserInfo >();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        List< UserInfo > list = new ArrayList<>();
        context.setAttribute("users", list);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // todo
    }
}
