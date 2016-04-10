package com.ksimeo.nazaru.view.controllers;

import com.ksimeo.nazaru.core.models.User;
import com.ksimeo.nazaru.view.requests.IRestSystem;
import com.ksimeo.nazaru.view.requests.RestSystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by @Ksimeo on 18.03.2015
 */
@WebServlet(urlPatterns = "/login")
public class LogInServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            req.setCharacterEncoding("UTF-8");
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            boolean isError = false;

            if (null == login || login.isEmpty()) isError = true;
            if (null == password || password.isEmpty()) isError = true;

            IRestSystem rs = new RestSystem();
            User user = rs.getUserByLoginPassw(login, password);
            System.out.println(user);


            if (user != null) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(30 * 60);
                String name = user.getLogin();
                Cookie userLogin = new Cookie("user", name);
                userLogin.setMaxAge(30 * 60);
                resp.addCookie(userLogin);
                if (user.isAdmin()) {
                    resp.sendRedirect("/secure/adminspage?page=1");
                } else {
                    resp.sendRedirect("/secure/userspage?page=1");
                }
            } else {
                req.setAttribute("Error", "Ви ввели помілковий логін або пароль!");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}