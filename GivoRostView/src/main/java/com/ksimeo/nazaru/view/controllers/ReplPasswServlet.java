package com.ksimeo.nazaru.view.controllers;

import com.ksimeo.nazaru.core.models.User;
import com.ksimeo.nazaru.view.requests.IRestSystem;
import com.ksimeo.nazaru.view.requests.RestSystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by @Ksimeo on 12.04.2015
 */
@WebServlet(urlPatterns = "/savepassw")
public class ReplPasswServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html; charset=UTF-8");
            String oldpassw = req.getParameter("oldpassw");
            String newpassw = req.getParameter("pass1");
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            String curlogin = user.getLogin();
            IRestSystem rs = new RestSystem();
            User usr = rs.getUserByLoginPassw(curlogin, oldpassw);
            System.out.println(usr);
            if (usr != null) {
                usr.setPassword(newpassw);
                rs.addNewUser(usr);
                resp.sendRedirect("/secure/userslist");
            } else {
                req.setAttribute("Error", "Ви ввели помілковий пароль!");
                req.getRequestDispatcher("/secure/changepassw.jsp").forward(req, resp);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Не сработало!");
        }
    }
}