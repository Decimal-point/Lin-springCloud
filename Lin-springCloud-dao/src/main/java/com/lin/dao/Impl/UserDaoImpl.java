package com.lin.dao.Impl;

import com.lin.dao.it.MongoMainDao;
import com.lin.dao.mongo.repository.UserRepository;
import com.lin.orm.UserBean;
import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public class UserDaoImpl implements MongoMainDao<UserBean,Long>{
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Boolean updata(Query query, Update updata) {


        WriteResult w = mongoTemplate.updateMulti(query, updata, UserBean.class);
        if (w.getN() > 0){
            return true;
        }
        return false;
    }

    @Override
    public List findByPage(Query query) {
        return mongoTemplate.find(query,UserBean.class);
    }

    @Override
    public Boolean saveOrUpdata(UserBean userBean) {
        try {
            mongoTemplate.save(userBean);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public UserBean findAndModify(Query query, Update update) {
        return mongoTemplate.findAndModify(query,update,UserBean.class);
    }

    @Override
    public Boolean deleteById(Long id) {
        try {
            userRepository.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Boolean delete(Query query) {
        try {
            mongoTemplate.remove(query,UserBean.class);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public UserBean findOne(Query query) {
        return mongoTemplate.findOne(query,UserBean.class);
    }

    @Override
    public Long count(Query query) {
        return mongoTemplate.count(query,UserBean.class);
    }
}
