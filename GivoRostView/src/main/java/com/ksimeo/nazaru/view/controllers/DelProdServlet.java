package com.ksimeo.nazaru.view.controllers;

import com.ksimeo.nazaru.view.requests.IRestSystem;
import com.ksimeo.nazaru.view.requests.RestSystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by @Ksimeo on 10.04.2015
 */
@WebServlet(urlPatterns = "/delproduct")
public class DelProdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html; charset=UTF-8");
            String prodid = req.getParameter("prodid");
            int id = Integer.parseInt(prodid);
//            System.out.println(prodid);
//            ProductService ps = ProductService.INSTANCE;
            IRestSystem rs = new RestSystem();
            rs.delProduct(id);
            resp.sendRedirect("/secure/productslist");
//            req.getRequestDispatcher("/secure/productslist").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
