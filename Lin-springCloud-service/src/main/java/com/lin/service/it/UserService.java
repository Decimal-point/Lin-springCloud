package com.lin.service.it;

import com.lin.orm.UserBean;

import java.util.List;
import java.util.Map;

public interface UserService {
    public Boolean updateMulti(Map<String,Object> query,Map<String,Object> update);

    public List<UserBean> findByPage(Map<String,Object> query, Map<String,Object> sort, Map<String,Object> page);

    public Boolean deleteById(Long id);

    public UserBean findOne(Map<String,Object> query);

    public Boolean saveAndUpdate(UserBean userBean);
}
