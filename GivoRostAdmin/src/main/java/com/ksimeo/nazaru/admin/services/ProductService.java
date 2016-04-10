package com.ksimeo.nazaru.admin.services;

import com.ksimeo.nazaru.core.models.Product;
import com.ksimeo.nazaru.admin.requests.IRestSystem;
import com.ksimeo.nazaru.admin.requests.RestSystem;

import java.util.List;

/**
 * Created by @Ksimeo on 13.03.2015
 */
public enum ProductService implements IProductService {

    INSTANCE;
    private IRestSystem rs;
//    private ProductDao prDao;

    private ProductService() {
        rs = new RestSystem();
    }

    @Override
    public List<Product> getAllProducts() {

        List<Product> toSend = (List<Product>) rs.getProducts();
        return toSend;
    }

    @Override
    public Product getProductById(int id) {

        return rs.getProductById(id);
    }

    @Override
    public void replaceProd(Product prod) {

        rs.changeProduct(prod);
    }

    public void delProductById(int id) {

        rs.delProduct(id);
    }
}
