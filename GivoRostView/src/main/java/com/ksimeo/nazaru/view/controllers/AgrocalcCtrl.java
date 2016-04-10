package com.ksimeo.nazaru.view.controllers;

import com.ksimeo.nazaru.core.models.Product;
import com.ksimeo.nazaru.view.services.ProductService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by @Ksimeo on 05.05.2015
 */
@WebServlet(urlPatterns = "/agrocalc")
public class AgrocalcCtrl extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = ProductService.INSTANCE.getAllProducts();
        req.setAttribute("products", products);
        req.getRequestDispatcher("WEB-INF/agrocalc.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
