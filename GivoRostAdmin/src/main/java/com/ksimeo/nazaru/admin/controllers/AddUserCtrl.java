package com.ksimeo.nazaru.admin.controllers;

import com.ksimeo.nazaru.core.models.User;
import com.ksimeo.nazaru.admin.requests.IRestSystem;
import com.ksimeo.nazaru.admin.requests.RestSystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by @author Ksimeo on 08.04.2015
 */
@WebServlet(urlPatterns = "/adduser")
public class AddUserCtrl extends HttpServlet {

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
            resp.sendRedirect("/WEB-INF/userslist");
        } else {
            req.setAttribute("Error", "Такий логiн вже існує!");
            req.getRequestDispatcher("/WEB-INF/createnewusr.jsp").forward(req, resp);
        }
    }
}
