package com.ksimeo.nazaru.rest.services;

import com.ksimeo.nazaru.core.models.User;

import java.util.List;
import java.util.Map;

/**
 * Created by @Ksimeo on 26.01.2015
 */
public interface IUserService {

    User addUser(User user);

    User getUserById(int id);

    public User getUser(Map<String, String> usermap);

    void deleteUserById(int id);

    //    User getByName(String name);
    List<User> getAllUsers();

    void deleteAllUsers();

    boolean isExistLogin(String login);
}