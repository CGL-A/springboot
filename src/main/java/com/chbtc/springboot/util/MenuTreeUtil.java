package com.chbtc.springboot.util;

import com.alibaba.druid.util.StringUtils;
import com.chbtc.springboot.model.Menu;
import com.chbtc.springboot.model.MenuRole;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chbtc on 2017/5/18.
 */
public class MenuTreeUtil {
    private List<Menu> nodes;

    private List<MenuRole> checknodes;


    /**
     * 创建一个新的实例 Tree.
     *
     * @param nodes   将树的所有节点都初始化进来。
     */
    public MenuTreeUtil(List<Menu> nodes, List<MenuRole> checknodes){
        this.nodes = nodes;
        this.checknodes = checknodes;
    }


    /**
     * buildTree
     * 描述:  创建树
     * @return List<Map<String,Object>>
     * @exception
     * @since  1.0.0
     */
    public List<Map<String, Object>> buildTree(){
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        for(Menu node : nodes) {
            //这里判断父节点，需要自己更改判断
            if (StringUtils.equals(node.getPid()+"", "0")) {
                //System.out.println("node"+node.getMenuName());
                Map<String, Object> map = buildTreeChildsMap(node);
                list.add(map);
            }
        }
        return list;
    }


    /**
     * buildChilds
     * 描述:  创建树下的节点。
     * @param node
     * @return List<Map<String,Object>>
     * @exception
     * @since  1.0.0
     */
    private List<Map<String, Object>> buildTreeChilds(Menu node){
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        List<Menu> childNodes = getChilds(node);
        for(Menu childNode : childNodes){
            //System.out.println("childNode"+childNode.getMenuName());
            Map<String, Object> map = buildTreeChildsMap(childNode);
            list.add(map);
        }
        return list;
    }

    /**
     * buildChildMap
     * 描述:生成Map节点
     * @param childNode
     * @return Map<String, Object>
     */
    private Map<String, Object> buildTreeChildsMap(Menu childNode){
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> stateMap = new HashMap<>();
        stateMap.put("checked", false);
        for(MenuRole checknode : checknodes){
            if(checknode.getMid().equals(childNode.getMid())){
                stateMap.put("checked", true);
            }
        }
        stateMap.put("disabled", false);
        stateMap.put("expanded", false);
        stateMap.put("selected", false);
        map.put("id", childNode.getMid());
        map.put("text", childNode.getMname());
        map.put("url", childNode.getUrl());
        map.put("state", stateMap);
        List<Map<String, Object>> childs = buildTreeChilds(childNode);
        if(childs.isEmpty() || childs.size() == 0){
            //map.put("state","open");
        }else{
            map.put("nodes", childs);
        }
        return map;
    }


    /**
     * getChilds
     * 描述:  获取子节点
     * @param parentNode
     * @return List<Resource>
     * @exception
     * @since  1.0.0
     */
    public List<Menu> getChilds(Menu parentNode) {
        List<Menu> childNodes = new ArrayList<Menu>();
        for(Menu node : nodes){
            //System.out.println(node.getParentId()+"-------"+parentNode.getId());
            if (StringUtils.equals(node.getPid()+"", parentNode.getMid()+"")) {
                childNodes.add(node);
            }
        }
        return childNodes;
    }

    /**
     * buildTree
     * 描述:  创建树
     * @return List<Map<String,Object>>
     * @exception
     * @since  1.0.0
     */
    public List<Menu> buildTreeGrid(){
        List<Menu> list = new ArrayList<Menu>();
        for(Menu node : nodes) {
            //这里判断父节点，需要自己更改判断
            if (StringUtils.equals(node.getPid()+"", "0")) {
                List<Menu> childs = buildTreeGridChilds(node);
                node.setChildList(childs);
                list.add(node);
            }
        }
        return list;
    }

    /**
     * buildChilds
     * 描述:  创建树下的节点。
     * @param node
     * @return List<Map<String,Object>>
     * @exception
     * @since  1.0.0
     */
    private List<Menu> buildTreeGridChilds(Menu node){
        List<Menu> list = new ArrayList<Menu>();
        List<Menu> childNodes = getChilds(node);
        for(Menu childNode : childNodes){
            //System.out.println("childNode"+childNode.getMenuName());
            List<Menu> childs = buildTreeGridChilds(childNode);
            childNode.setChildList(childs);
            list.add(childNode);
        }
        return list;
    }
}
