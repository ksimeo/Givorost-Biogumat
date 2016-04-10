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
 * Created by @author Ksimeo on 10.04.2015
 */
@WebServlet(urlPatterns = "/changeproduct")
public class EditProdCtrl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html; charset=UTF-8");
            String prodId = req.getParameter("id");
            int id = Integer.parseInt(prodId);
            IRestSystem rs = new RestSystem();
            Product prod = rs.getProductById(id);
            req.setAttribute("prodid", prod.getId());
            req.setAttribute("productname", prod.getName());
            req.setAttribute("price", prod.getPrice());
            req.setAttribute("factor", prod.getFactor());
            String prodFactor = req.getParameter("factor");
            req.getRequestDispatcher("WEB-INF/corrprod.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html; charset=UTF-8");
            String prodId = req.getParameter("prodid");
            int id = Integer.parseInt(prodId);
//            ProductService ps = ProductService.INSTANCE;
            IRestSystem rs = new RestSystem();
            Product prod = rs.getProductById(id);
            String prodName = req.getParameter("productname");
            String prodPrice = req.getParameter("price");
            String prodFactor = req.getParameter("factor");
            try {
                float price = Float.parseFloat(prodPrice);
                float factor = Float.parseFloat(prodFactor);
                prod.setName(prodName);
                prod.setPrice(price);
                prod.setFactor(factor);
                rs.changeProduct(prod);
                resp.sendRedirect("/WEB-INF/productslist");

            } catch (Exception e) {
                req.setAttribute("Error", "Ви ввели не число!");
                req.getRequestDispatcher("/changeproduct?id=" + prod.getId()).forward(req, resp);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
