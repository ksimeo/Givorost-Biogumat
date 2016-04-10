package com.ksimeo.nazaru.view.requests;

import com.ksimeo.nazaru.core.models.Order;
import com.ksimeo.nazaru.core.models.Product;
import com.ksimeo.nazaru.core.models.User;

import java.util.List;

/**
 * Created by @Ksimeo on 16.04.2015
 */
public interface IRestSystem {

    void sendOrder(Order order);

    void delOrder(int id);

    List getOrderPage(int page);

    List getOrders(int page);

    long getOrdersCount();

    List getOrderGroup(int from, int to);

    void delAllOrders();

    void addNewUser(User user);

    User getUserByLoginPassw(String login, String passw);

    boolean isLoginExist(String login);

    void delUser(int id);

    List getUsers();

    User getUser(int id);

    void addNewProduct(Product product);

    List getProducts();

    void delProduct(int id);

    Product getProductById(int id);

    void changeProduct(Product product);
}
