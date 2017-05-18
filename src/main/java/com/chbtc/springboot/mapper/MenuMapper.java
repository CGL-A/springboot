package com.chbtc.springboot.mapper;

import com.chbtc.springboot.model.Menu;
import java.util.List;
import java.util.Set;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer mid);

    int insert(Menu record);

    Menu selectByPrimaryKey(Integer mid);

    List<Menu> selectAll();

    int updateByPrimaryKey(Menu record);

    Set<String> getMenuByUserId(Integer uid);

}