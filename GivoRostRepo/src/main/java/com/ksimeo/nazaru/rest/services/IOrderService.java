package com.ksimeo.nazaru.rest.services;

import com.ksimeo.nazaru.core.models.Order;
import java.util.List;

/**
 * Created by Ksimeo on 26.01.2015.
 */
public interface IOrderService {

    void addNewOrder(Order order);
    List<Order> getOrders(int page);
    List<Order> getPage(int from, int to);
    List<Order> getAllOrders();
    void deleteAllOrders();
    void deleteOrder(int id);
    long getCount();
}