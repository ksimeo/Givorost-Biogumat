package com.ksimeo.nazaru.rest.services;

import com.ksimeo.nazaru.core.models.Order;
import com.ksimeo.nazaru.rest.dao.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @Ksimeo on 26.01.2015
 */
@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderDao;

    @Override
    public void addNewOrder(Order order) {
        int lastId = getLastId();
        order.setId(++lastId);
        orderDao.save(order);
    }

    @Override
    public List<Order> getOrders(int page) {
        return (List<Order>)orderDao.findAll();

    }

    @Override
    public long getCount() {
        return orderDao.count();
    }

    @Override
    public List<Order> getPage(int from, int to) {
        List<Order> toSend = new ArrayList<>();
        if (from < to) {
            for (int i = to; i >= from; i--) {
                Order order = orderDao.findOne(i);
                if (order == null) continue;
                toSend.add(order);
            }
        } else if (from == to) {
            toSend.add(orderDao.findOne(from));
        }
        return toSend;
    }

    @Override
    public List<Order> getAllOrders() {

        return (ArrayList<Order>) orderDao.findAll();
    }

    @Override
    public void deleteAllOrders() {

        orderDao.deleteAll();
    }

    @Override
    public void deleteOrder(int id) {

        orderDao.delete(id);
        List<Order> orders = (List<Order>) orderDao.findAll();
        orderDao.deleteAll();
        int i = 1;
        for (Order order : orders) {
            order.setId(i++);
            orderDao.save(order);
        }
    }

    private int getLastId() {
        List<Order> orders = (List<Order>) orderDao.findAll();
        int maxId = 0;
        for (Order order : orders) {
            if (order.getId() > maxId) maxId = order.getId();
        }
        return maxId;
    }
}