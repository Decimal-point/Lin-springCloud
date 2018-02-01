package com.lin.dao.mongo.repository;

import com.lin.orm.UserBean;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserBean,Long> {
}
