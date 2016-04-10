package com.ksimeo.nazaru.rest.controllers;

import com.ksimeo.nazaru.core.models.User;
import com.ksimeo.nazaru.rest.services.IUserService;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by @Ksimeo on 23.01.2015
 */
@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/addnewuser", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public User addNewUser(@RequestBody String jsonData) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(jsonData, User.class);
        return userService.addUser(user);
    }

    @RequestMapping(value = "deluserbyid/{id}/", method = RequestMethod.GET)
    @ResponseBody
    public void delUserById(@PathVariable int id) {

        userService.deleteUserById(id);
    }

    @RequestMapping(value = "getuserbyid/{id}/", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public User getUserById(@PathVariable int id) {

        return userService.getUserById(id);
    }

    @RequestMapping(value = "/getallusers", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<User> getAllUsers() throws IOException {

        return userService.getAllUsers();
    }

    @RequestMapping(value = "/getuserbyloginpassw", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    @ResponseBody
    public User getUserById(@RequestBody String jsonData) {
        try {
            ObjectMapper om = new ObjectMapper();
            Map<String, String> usermap = om.readValue(jsonData, new TypeReference<Map<String, String>>() {
            });
            return userService.getUser(usermap);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @RequestMapping(value = "isexist", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE
            + ";charset=UTF-8", produces = "application/json")
    @ResponseBody
    private boolean isExistLogin(@RequestBody String login) {

        return userService.isExistLogin(login);
    }
}