package com.ksimeo.nazaru.admin.requests;

import com.ksimeo.nazaru.core.models.Order;
import com.ksimeo.nazaru.core.models.Product;
import com.ksimeo.nazaru.core.models.User;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by @Ksimeo on 16.04.2015
 */
public class RestSystem implements IRestSystem {

    private String urlBase = "http://localhost:6060";

//    @Override
    public void sendOrder(Order order) {
        try {
            ObjectMapper om = new ObjectMapper();
            String data = om.writeValueAsString(order);
            data = new String(data.getBytes("utf-8"), "ISO-8859-1");
            String fullUrl = urlBase + "/addneworder";
            System.out.println(order);
            sendPost(fullUrl, data);
//
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
    public void delOrder(int id) {
        try {
            String fullUrl = urlBase + "/delorder/" + id;
            sendGet(fullUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
    public long getOrdersCount() {
        try {
            String data = sendGet(urlBase + "/getorderscount");
            long toSend = Integer.parseInt(data);
            System.out.println(toSend);
            return toSend;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

//    @Override
    public List getOrderPage(int page) {
        try {
            ObjectMapper om = new ObjectMapper();
            String fullUrl = urlBase + "/getorderspage/" + page;
            String data = sendGet(fullUrl);
            data = new String(data.getBytes("cp1251"), "utf-8");
            List<Order> toSend = om.readValue(data, new TypeReference<List<Order>>() {
            });
            System.out.println(toSend);
            return toSend;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    @Override
    public List getOrders(int page) {
        try {
            String fullPath = urlBase + "/getorder/" + page + "/";
            String orderData = sendGet(fullPath);
            ObjectMapper om = new ObjectMapper();
            List<Order> toSend = om.readValue(orderData, new TypeReference<List<Order>>() {
            });
            return toSend;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    @Override
    public List getOrderGroup(int from, int to) {
        try {
            ObjectMapper om = new ObjectMapper();
            String fullUrl = urlBase + "/from/" + from + "/to/" + to;
            String data = sendGet(fullUrl);
            data = new String(data.getBytes("cp1251"), "utf-8");
            return om.readValue(data, new TypeReference<List<Order>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    @Override
    public void delAllOrders() {
        try {
            String fullPath = urlBase + "/delallorders/";
            sendGet(fullPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    @Override
    public void addNewUser(User user) {
        try {
            String fullPath = urlBase + "/addnewuser/";
            ObjectMapper om = new ObjectMapper();
            String data = om.writeValueAsString(user);
            data = new String(data.getBytes("utf-8"), "ISO-8859-1");
            sendPost(fullPath, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserByLoginPassw(String login, String passw) {
        try {
            String fullPath = urlBase + "/getuserbyloginpassw";
            Map<String, String> usermap = new HashMap<String, String>();
            usermap.put("login", login);
            usermap.put("password", passw);
            System.out.println(usermap);
            ObjectMapper om = new ObjectMapper();
            String sendData = om.writeValueAsString(usermap);
            String orderData = sendPost(fullPath, sendData);
            orderData = new String(orderData.getBytes("cp1251"), "utf-8");
            User toSend = om.readValue(orderData, User.class);
            System.out.println(toSend);
            return toSend;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    @Override
    public boolean isLoginExist(String login) {
        try {
            String fullPath = urlBase + "/isexist";
            String data = sendPost(fullPath, login);
            return Boolean.parseBoolean(data);
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

//    @Override
    public void delUser(int id) {
        try {
            String fullPath = urlBase + "/deluserbyid/" + id + "/";
            sendGet(fullPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
    public List getUsers() {
        try {
            String fullPath = urlBase + "/getallusers/";
            String orderData = sendGet(fullPath);
            orderData = new String(orderData.getBytes("cp1251"), "utf-8");
            ObjectMapper om = new ObjectMapper();
            List<User> toSend = om.readValue(orderData, new TypeReference<List<User>>() {
            });
            return toSend;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    @Override
    public User getUser(int id) {
        try {
            ObjectMapper om = new ObjectMapper();
            String fullPath = urlBase + "getuserbyid/" + id + "/";
            String data = sendGet(fullPath);
            data = new String(data.getBytes("cp1251"), "utf-8");
            User user = om.readValue(data, User.class);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    @Override
    public void addNewProduct(Product prod) {
        try {
            ObjectMapper om = new ObjectMapper();
            String data = om.writeValueAsString(prod);
            System.out.println(data);
            data = new String(data.getBytes("utf-8"), "ISO-8859-1");
            String fullUrl = urlBase + "/addnewproduct";
            sendPost(fullUrl, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
    public List getProducts() {
        try {
            String fullPath = urlBase + "/getallproducts/";
            String data = sendGet(fullPath);
            data = new String(data.getBytes("cp1251"), "utf-8");
            ObjectMapper om = new ObjectMapper();
            List<Product> toSend = om.readValue(data, new TypeReference<List<Product>>() {
            });
            return toSend;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void delProduct(int id) {
        try {
            String fullPath = urlBase + "/delproduct/" + id + "/";
            sendGet(fullPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    @Override
    public Product getProductById(int id) {
        try {
            String fullPath = urlBase + "/getproductbyid/" + id + "/";
            String data = sendGet(fullPath);
            data = new String(data.getBytes("cp1251"), "utf-8");
            ObjectMapper om = new ObjectMapper();
            Product toSend = om.readValue(data, Product.class);
            return toSend;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    @Override
    public void changeProduct(Product prod) {
        try {
            ObjectMapper om = new ObjectMapper();
            String data = om.writeValueAsString(prod);
            data = new String(data.getBytes("utf-8"), "ISO-8859-1");
            String fullUrl = urlBase + "/changeproduct";
            sendPost(fullUrl, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String sendGet(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    // HTTP POST request
    private String sendPost(String url, String data) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(data);
        wr.flush();
        wr.close();
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}
