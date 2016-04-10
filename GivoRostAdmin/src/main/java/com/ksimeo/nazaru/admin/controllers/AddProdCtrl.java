package com.ksimeo.nazaru.admin.controllers;

import com.ksimeo.nazaru.core.models.Product;
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
@WebServlet(urlPatterns = "/addproduct")
public class AddProdCtrl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String name = req.getParameter("productname");
        float price = Float.parseFloat(req.getParameter("price"));
        float factor = Float.parseFloat(req.getParameter("factor"));
        boolean isError = false;
        if (name == null || name.isEmpty()) isError = true;
        if (price == 0f) isError = true;
        if (factor == 0f) isError = true;
        if (!isError) {
            Product product = new Product(name, price, factor);
            IRestSystem rs = new RestSystem();
            rs.addNewProduct(product);
            resp.sendRedirect("/WEB-INF/productslist");
        } else {
            req.setAttribute("Error", "Ви ввели не усі данні!");
            req.getRequestDispatcher("/WEB-INF/createnewprod.jsp").forward(req, resp);
        }
    }
}