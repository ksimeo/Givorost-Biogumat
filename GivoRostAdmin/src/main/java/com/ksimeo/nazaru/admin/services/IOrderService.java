package com.ksimeo.nazaru.admin.services;

import com.ksimeo.nazaru.core.models.Order;
import com.ksimeo.nazaru.core.models.Parcel;

import java.util.List;

/**
 * Created by @Ksimeo on 18.03.2015
 */
public interface IOrderService {

    List<Order> getAllOrders();
    void saveNewOrder(Order order);
    List<Order> getOrders(int from, int to);
//    List<Order> getOrders(int page);
    Parcel<Order> getOrdersPage();
    Parcel<Order> getOrdersPage(int page);
//    List<Order> getPage();
    int getLastId();
}
