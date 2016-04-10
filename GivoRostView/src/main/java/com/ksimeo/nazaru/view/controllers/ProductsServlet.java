package com.ksimeo.nazaru.view.controllers;

import com.ksimeo.nazaru.core.models.Product;
import com.ksimeo.nazaru.view.services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by @Ksimeo on 06.04.2015
 */
@WebServlet(urlPatterns = "/secure/productslist")
public class ProductsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Product> products = ProductService.INSTANCE.getAllProducts();
            req.setAttribute("products", products);
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html; charset=UTF-8");
            req.getRequestDispatcher("/secure/productslist.jsp").forward(req, resp);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { }
}
