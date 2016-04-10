package com.ksimeo.nazaru.admin.controllers;

import com.ksimeo.nazaru.core.models.User;
import com.ksimeo.nazaru.admin.requests.IRestSystem;
import com.ksimeo.nazaru.admin.requests.RestSystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by @author Ksimeo on 07.04.2015
 */
@WebServlet(urlPatterns = "/deluser")
public class DelUserCtrl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("application/json");
            resp.setContentType("text/html; charset=UTF-8");
            String id = req.getParameter("userid");
            int userId = Integer.parseInt(id);
            IRestSystem rs = new RestSystem();
            HttpSession session = req.getSession();
            User curUser = (User) session.getAttribute("user");
            int curUsrId = curUser.getId();
            if (userId != curUsrId) {
                rs.delUser(userId);
                resp.sendRedirect("/WEB-INF/userslist");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
