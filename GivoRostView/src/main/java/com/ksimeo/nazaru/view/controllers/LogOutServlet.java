package com.ksimeo.nazaru.view.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by @Ksimeo on 30.03.2015
 */
@WebServlet(urlPatterns = "/logout")
public class LogOutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession(false);

            if (session != null) {
                session.removeAttribute("user");
                session.invalidate();
            }
            resp.sendRedirect("/login.jsp");
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}
