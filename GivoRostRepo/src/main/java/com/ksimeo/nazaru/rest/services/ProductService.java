package com.ksimeo.nazaru.rest.services;

import com.ksimeo.nazaru.core.models.Product;
import com.ksimeo.nazaru.rest.dao.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by @Ksimeo on 26.01.2015
 */
@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productDao;

    @Override
    public Product addNewProduct(Product prod) {
        int lastId = getLastId();
        prod.setId(++lastId);
        productDao.save(prod);
        return prod;
    }

    @Override
    public List<Product> getAllProducts() {

        return (List<Product>) productDao.findAll();
    }

    @Override
    public void delProduct(int id) {

        productDao.delete(id);
        List<Product> products = (List<Product>) productDao.findAll();
        productDao.deleteAll();
        int i = 1;
        for (Product prod : products) {
            prod.setId(i++);
            productDao.save(prod);
        }
    }

    @Override
    public void deleteAllProducts() {

        productDao.deleteAll();
    }

    @Override
    public Product getById(int id) {

        return productDao.findOne(id);
    }

    @Override
    public void changeProduct(Product product) {

        productDao.delete(product.getId());
        productDao.save(product);
    }

    private int getLastId() {
        List<Product> prods = (List<Product>) productDao.findAll();
        int maxId = 0;
        for (Product prod : prods) {
            if (prod.getId() > maxId) maxId = prod.getId();
        }
        return maxId;
    }
}
