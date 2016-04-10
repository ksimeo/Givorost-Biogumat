package com.ksimeo.nazaru.view.services;

import com.ksimeo.nazaru.core.models.User;

import java.util.List;

/**
 * Created by @Ksimeo on 08.04.2015
 */
public interface IUserService {

    List<User> getUsers();

    //    Map<String, User> getUsersData();
    void delById(int id);

    User getById(int id);
}

