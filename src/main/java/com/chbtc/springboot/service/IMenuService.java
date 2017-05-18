package com.chbtc.springboot.service;

import com.chbtc.springboot.model.Menu;

import java.util.List;
import java.util.Set;

/**
 * Created by chbtc on 2017/5/18.
 */
public interface IMenuService {
    List<Menu> getMenuList();
    Set<String> getMenuByUserId(Integer uid);
}
