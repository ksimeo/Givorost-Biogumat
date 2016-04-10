package com.ksimeo.nazaru.view.controllers;

import com.ksimeo.nazaru.core.models.Order;
import com.ksimeo.nazaru.core.models.Parcel;
import com.ksimeo.nazaru.core.models.User;
import com.ksimeo.nazaru.view.requests.IRestSystem;
import com.ksimeo.nazaru.view.requests.RestSystem;
import com.ksimeo.nazaru.view.services.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by @Ksimeo on 18.03.2015
 */
@WebServlet(urlPatterns = "/secure/adminspage")
public class AdminsPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int page = Integer.parseInt(req.getParameter("page"));
            OrderService os = OrderService.INSTANCE;
            HttpSession session = req.getSession();
            User user = (User)session.getAttribute("user");
            String username = user.getName();
            req.setAttribute("username", username);
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html; charset=UTF-8");
            int p = page;
            IRestSystem rs = new RestSystem();
            int rowNumb = 8;
            long numb = rs.getOrdersCount();
            int quot = (int) numb / rowNumb;
            int maxPage = quot != 0 ? quot : 1;

            if (numb % rowNumb != 0) {
                maxPage++;
            }
            int from;
            int to;
            if (page > maxPage) page = maxPage;

            if(page > 0) {

                to = (int) (numb - ((page - 1) * rowNumb));
                from = to - (rowNumb - 1);
            } else {
                page = 1;
                to = (int) (numb - ((page - 1) * rowNumb));
                from = to - (rowNumb - 1);
            }

            if(from <= 0) from = 1;
            if (to > numb) to = (int) numb;
            List<Order> orderGroup = rs.getOrderGroup(from, to);
            Parcel<Order> ordersPage = new Parcel(page, orderGroup);
            req.setAttribute("orders", orderGroup);
            req.setAttribute("page", page);
            req.getRequestDispatcher("/secure/adminspage.jsp").forward(req, resp);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { }
}