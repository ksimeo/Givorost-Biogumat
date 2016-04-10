package com.ksimeo.nazaru.view.controllers;

import com.ksimeo.nazaru.core.models.User;
import com.ksimeo.nazaru.view.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by @author Ksimeo on 18.03.2015
 */
@WebServlet(urlPatterns = "/WEB-INF/userslist")
public class UsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<User> users = UserService.INSTANCE.getUsers();
            req.setAttribute("users", users);
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html; charset=UTF-8");
            req.getRequestDispatcher("/WEB-INF/userslist.jsp").forward(req, resp);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { }
}
