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
@WebServlet(urlPatterns = "/WEB-INF/corrprod")
public class CorrProdCtrl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html; charset=UTF-8");
            String prodId = req.getParameter("id");
            int id = Integer.parseInt(prodId);
            IRestSystem rs = new RestSystem();
            Product prod = rs.getProductById(id);
            String oldName = prod.getName();
            float oldPrice = prod.getPrice();
            float oldFactor = prod.getFactor();
            req.setAttribute("productname", oldName);
            req.setAttribute("price", oldPrice);
            req.setAttribute("factor", oldFactor);
            req.getRequestDispatcher("/WEB-INF/corrprod.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
