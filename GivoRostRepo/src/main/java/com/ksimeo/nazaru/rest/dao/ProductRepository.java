package com.ksimeo.nazaru.rest.dao;

import com.ksimeo.nazaru.core.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by @Ksimeo on 03.05.2015
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
}