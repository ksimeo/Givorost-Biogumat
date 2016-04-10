package com.ksimeo.nazaru.view.controllers;

import com.ksimeo.nazaru.core.models.User;
import com.ksimeo.nazaru.view.requests.IRestSystem;
import com.ksimeo.nazaru.view.requests.RestSystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by @Ksimeo on 08.04.2015
 */
@WebServlet(urlPatterns = "/adduser")
public class NewUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String name = req.getParameter("username");
        String login = req.getParameter("userlogin");
        String password = req.getParameter("pass1");
        String adminStatus = req.getParameter("adminstatus");
        boolean isAdmin = Boolean.parseBoolean(adminStatus);
        IRestSystem rs = new RestSystem();
        if (!rs.isLoginExist(login)) {
            User user = new User(name, login, password, isAdmin);
            rs.addNewUser(user);
            resp.sendRedirect("/secure/userslist");
        } else {
            req.setAttribute("Error", "Такий логiн вже існує!");
            req.getRequestDispatcher("/secure/createnewusr.jsp").forward(req, resp);
        }
    }
}
