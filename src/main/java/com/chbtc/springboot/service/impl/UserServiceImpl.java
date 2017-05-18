package com.chbtc.springboot.service.impl;

import com.chbtc.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chbtc on 2017/5/17.
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUserById(Integer id) {
        return  userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
      return  userMapper.insert(record);
    }
}
