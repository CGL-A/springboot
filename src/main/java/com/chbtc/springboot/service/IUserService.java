package com.chbtc.springboot.service;

import com.chbtc.springboot.model.User;

/**
 * Created by chbtc on 2017/5/17.
 */
public interface IUserService {
    User getUserById(Integer id);
    int insert(User record);
    User findByName(String name);
}
