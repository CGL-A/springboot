package com.chbtc.springboot.service.impl;

import com.chbtc.springboot.mapper.MenuMapper;
import com.chbtc.springboot.model.Menu;
import com.chbtc.springboot.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by chbtc on 2017/5/18.
 */
@Service
public class MenuService implements IMenuService {
    @Autowired
    MenuMapper menuMapper;
    @Override
    public List<Menu> getMenuList() {
       return  menuMapper.selectAll();
    }

    @Override
    public Set<String> getMenuByUserId(Integer uid) {
        return menuMapper.getMenuByUserId(uid);
    }
}
