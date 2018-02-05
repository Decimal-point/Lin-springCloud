package com.lin.service.Impl;

import com.lin.dao.it.MongoMainDao;
import com.lin.orm.UserBean;
import com.lin.service.it.UserService;
import com.lin.service.utils.QueryMongoUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class UserServiceImpl implements UserService {
    private MongoMainDao<UserBean,Long> userDao;
    @Override
    public Boolean updateMulti(Map<String, Object> query, Map<String, Object> update) {
        if (MapUtils.isEmpty(query)){
            return false;
        }
        update.remove("id");
        update.remove("_id");
        if (MapUtils.isEmpty(update)){
            return false;
        }
        QueryMongoUtils queryMongoUtils = new QueryMongoUtils();
        Criteria requestRestriction = queryMongoUtils.getRequestRestriction(query);
        Query mquery = new Query(requestRestriction);
        Update mupdate = queryMongoUtils._getRequestUpdate(update);
        return userDao.updata(mquery,mupdate);
    }

    @Override
    public List<UserBean> findByPage(Map<String, Object> query, Map<String, Object> sort, Map<String, Object> page) {
        QueryMongoUtils queryMongoUtils = new QueryMongoUtils();
        Sort requestSort = queryMongoUtils.getRequestSort(sort);
        Criteria requestRestriction = queryMongoUtils.getRequestRestriction(query);
        Pageable pageRequest = queryMongoUtils.getPageRequest(page);
        Query mquery = new Query(requestRestriction);
        return userDao.findByPage(mquery.with(requestSort).with(pageRequest));
    }

    @Override
    public Boolean deleteById(Long id) {
        return userDao.deleteById(id);
    }

    @Override
    public UserBean findOne(Map<String, Object> query) {
        QueryMongoUtils queryMongoUtils = new QueryMongoUtils();
        Criteria requestRestriction = queryMongoUtils.getRequestRestriction(query);
        Query q = new Query(requestRestriction);
        return userDao.findOne(q);
    }

    @Override
    public Boolean saveAndUpdate(UserBean userBean) {
        return userDao.saveOrUpdata(userBean);
    }
}
