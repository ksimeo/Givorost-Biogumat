package com.ksimeo.nazaru.admin.controllers;

import com.ksimeo.nazaru.admin.requests.IRestSystem;
import com.ksimeo.nazaru.admin.requests.RestSystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by @author Ksimeo on 18.05.2015
 */
@WebServlet(urlPatterns = "/delorder")
public class DelOrderCtrl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html; charset=UTF-8");
            String orderId = req.getParameter("orderid");
            String page = req.getParameter("currpage");
            int id = Integer.parseInt(orderId);
            IRestSystem rs = new RestSystem();
            rs.delOrder(id);
            resp.sendRedirect("/WEB-INF/adminspage?page=" + page);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
