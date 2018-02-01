package com.lin.dao.it;


import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface MongoMainDao<T,ID> {

    public Boolean updata(Query query, Update updata);

    public List<T> findByPage(Query query);

    public Boolean saveOrUpdata(T entity);

    public T findAndModify(Query query,Update update);

    public Boolean deleteById(Long id);

    public Boolean delete(Query query);

    public T findOne(Query query);

    public Long count(Query query);
}
