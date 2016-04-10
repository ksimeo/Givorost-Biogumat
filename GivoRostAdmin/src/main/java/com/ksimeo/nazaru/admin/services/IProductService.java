package com.ksimeo.nazaru.admin.services;

import com.ksimeo.nazaru.core.models.Product;

import java.util.List;

/**
 * Created by @Ksimeo on 13.03.2015
 */
public interface IProductService {

    List<Product> getAllProducts();
    Product getProductById(int id);
    void replaceProd(Product prod);
//    List<String> getProductsName();
}
