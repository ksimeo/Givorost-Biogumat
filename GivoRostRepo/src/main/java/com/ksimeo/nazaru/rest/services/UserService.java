package com.ksimeo.nazaru.rest.services;

import com.ksimeo.nazaru.core.models.User;
import com.ksimeo.nazaru.rest.dao.UserRepository;
import com.ksimeo.nazaru.rest.helpers.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by @Ksimeo on 26.01.2015
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userDao;

    @Override
    public User addUser(User user) {
        int lastId = getLastId();
        user.setId(++lastId);
        try {
            user.setPassword(UserHelper.String2Hash(user.getPassword()));
            return userDao.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User getUserById(int id) {

        return userDao.findOne(id);
    }

    @Override
    public void deleteUserById(int id) {

        userDao.delete(id);
        List<User> users = (List<User>) userDao.findAll();
        userDao.deleteAll();
        int i = 1;
        for (User user : users) {
            user.setId(i++);
            userDao.save(user);
        }
    }

    @Override
    public boolean isExistLogin(String login) {

        List<User> users = (List<User>) userDao.findAll();
        for (User user : users) {
            if (user.getLogin().equalsIgnoreCase(login)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() {

        return (ArrayList<User>) userDao.findAll();
    }

    @Override
    public void deleteAllUsers() {

        userDao.deleteAll();
    }

    @Override
    public User getUser(Map<String, String> usermap) {
        try {
            List<User> users = (List<User>) userDao.findAll();
            String login = usermap.get("login");
            String password = usermap.get("password");
            password = UserHelper.String2Hash(password);
            for (User user : users) {
                if (user.getLogin().equalsIgnoreCase(login)) {
                    if (user.getPassword().equals(password)) {
                        return user;
                    } else {
                        return null;
                    }
                }
            }
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private int getLastId() {
        List<User> users = (List<User>) userDao.findAll();
        int maxId = 0;
        for (User user : users) {
            if (user.getId() > maxId) maxId = user.getId();
        }
        return maxId;
    }
}