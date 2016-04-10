package com.ksimeo.nazaru.admin.services;

import com.ksimeo.nazaru.core.models.Order;
import com.ksimeo.nazaru.core.models.Parcel;
import com.ksimeo.nazaru.admin.dao.IOrderDao;
import com.ksimeo.nazaru.admin.requests.IRestSystem;
import com.ksimeo.nazaru.admin.requests.RestSystem;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by @Ksimeo on 18.03.2015
 */
public enum OrderService implements IOrderService {

    INSTANCE;

    private AtomicInteger lastId;
    private IOrderDao orderDao;
    private IRestSystem rs;

    private OrderService() {
        rs = new RestSystem();
//        orderDao = new OrderDao();
//        int lid = orderDao.getLastId();
    }

    @Override
    public List<Order> getAllOrders() {

        return orderDao.getOrders();
    }

    @Override
    public void saveNewOrder(Order order) {

        orderDao.saveOrder(order);
//        restSys.addNewOrder(order);

    }

    @Override
    public List<Order> getOrders(int from, int to) {

        return orderDao.getPage(from, to);
    }


//    @Override
//    public Parcel<Order> getOrdersPage(int page) {
//        int from = ((page - 1) * 5);
//        int to = page * 5 - 1;
//        List<Order> orderPage = orderDao.getPage(from, to);
////        Parcel<Order> pageList = new Parcel<>();
////        if(!orderPage.isEmpty()) {
////            pageList.setPage(orderPage);
////            pageList.setCount(page);
////            return pageList;
////        }
//        return new Parcel<Order>(page, orderPage);
//    }

    @Override
    public Parcel<Order> getOrdersPage(int page) {

        List<Order> orderPage = rs.getOrderPage(page);
        return new Parcel<Order>(page, orderPage);
    }

    @Override
    public Parcel<Order> getOrdersPage() {

        return orderDao.getOrdersPage();
    }

//    public List<Order> getPage() {
//
//        return orderDao.getPage();
//    }

    @Override
    public int getLastId() {

        return orderDao.getLastId();
    }

    public Order getOrderById(int id) {

        return null;
    }
}
