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
 * Created by  @author Ksimeo on 10.04.2015
 */
@WebServlet(urlPatterns = "/delproduct")
public class DelProdCtrl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html; charset=UTF-8");
            String prodid = req.getParameter("prodid");
            int id = Integer.parseInt(prodid);
            IRestSystem rs = new RestSystem();
            rs.delProduct(id);
            resp.sendRedirect("/WEB-INF/productslist");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
