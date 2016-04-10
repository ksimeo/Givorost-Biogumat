package com.ksimeo.nazaru.view.controllers;

import com.ksimeo.nazaru.core.models.Order;
import com.ksimeo.nazaru.core.models.Product;
import com.ksimeo.nazaru.view.requests.IRestSystem;
import com.ksimeo.nazaru.view.requests.RestSystem;
import com.ksimeo.nazaru.view.services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by @Ksimeo on 13.03.2015
 */
@WebServlet(urlPatterns = "/ordering")
public class DoOrderCtrl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String number = req.getParameter("numb");
        req.setAttribute("number", number);
        String prodType = req.getParameter("type");
        req.setAttribute("prodType", prodType);
        List<Product> products = ProductService.INSTANCE.getAllProducts();
        req.setAttribute("products", products);
        req.getRequestDispatcher("WEB-INF/ordering.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html; charset=UTF-8");
            String tel = req.getParameter("telnumber");
            String name = req.getParameter("ordername");
            String email = req.getParameter("address");
            String region = req.getParameter("region");
            String product = req.getParameter("product");
            String number = req.getParameter("number");
            boolean isError = false;

            if (null == tel || tel.isEmpty()) isError = true;
            if (null == number || number.isEmpty()) isError = true;

            if (!isError) {

                IRestSystem rs = new RestSystem();
                int numb = Integer.parseInt(number);
                if (name.isEmpty()) name = "Не вказано";
                if (email.isEmpty()) email = "Не вказано";
                if (region.equals(" ")) region = "Не вказано";
                Order order = new Order(tel, name, email, region, product, numb);
                rs.sendOrder(order);
                resp.sendRedirect("WEB-INF/ordered.html");
            } else {
                req.setAttribute("Error", "Ви ввели не усі данні!");
                req.getRequestDispatcher("WEB-INF/ordering.html").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}