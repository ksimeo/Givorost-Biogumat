package com.ksimeo.nazaru.rest.controllers;

import com.ksimeo.nazaru.core.models.Product;
import com.ksimeo.nazaru.rest.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by @Ksimeo on 23.01.2015
 */
@Controller
public class ProductController {

    @Autowired
    private IProductService productService;

    @RequestMapping(value = "/addnewproduct", consumes = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    @ResponseBody
    public void addNewProduct(@RequestBody Product product) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        data = new String (data.getBytes ("cp1251"), "utf-8");
//        Product product = mapper.readValue(data, Product.class);
        System.out.println(product);
        productService.addNewProduct(product);
    }

    @RequestMapping(value = "getproductbyid/{id}/", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Product getProductById(@PathVariable int id) {

        return productService.getById(id);
    }

    @RequestMapping(value = "/delproduct/{id}/", method = RequestMethod.GET)
    @ResponseBody
    public void delProduct(@PathVariable int id) throws IOException {

        productService.delProduct(id);
    }

    @RequestMapping(value = "/changeproduct", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    @ResponseBody
    public void changeProduct(@RequestBody Product product) throws IOException {

        productService.changeProduct(product);
    }

    @RequestMapping(value = "/getallproducts", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Product> getAllProducts() throws IOException {

        return productService.getAllProducts();
    }

    @RequestMapping(value = "/delallproducts", method = RequestMethod.DELETE, produces = "application/json")
    public String delAllProducts() throws IOException {

        productService.deleteAllProducts();
        return "all_products_deleted";
    }
}