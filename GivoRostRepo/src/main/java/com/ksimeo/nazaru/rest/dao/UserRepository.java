package com.ksimeo.nazaru.rest.dao;

import com.ksimeo.nazaru.core.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by @Ksimeo on 03.05.2015
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}