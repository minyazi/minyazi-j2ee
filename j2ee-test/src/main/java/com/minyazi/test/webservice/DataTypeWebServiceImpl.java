package com.minyazi.test.webservice;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.minyazi.core.util.LogUtil;

public class DataTypeWebServiceImpl implements DataTypeWebService {
    @Override
    public boolean isExist(User user) {
        if (user != null && user.getId() != null) {
            if (user.getId().equals("1") || user.equals("2")) {
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public String getUserName(String id) {
        if (id != null) {
            if (id.equals("1")) {
                return "张三";
            } else if (id.equals("2")) {
                return "李四";
            }
        }
        
        return "";
    }
    
    @Override
    public User getUserById(String id) {
        if (id != null) {
            if (id.equals("1")) {
                return new User("1", "张三");
            } else if (id.equals("2")) {
                return new User("2", "李四");
            }
        }
        
        return null;
    }
    
    @Override
    public List<User> getUsersByName(String name) {
        List<User> users = new ArrayList<User>();
        if (name != null) {
            if (name.equals("张三")) {
                users.add(new User("1", "张三"));
            } else if (name.equals("李四")) {
                users.add(new User("2", "李四"));
            }
        }
        return users;
    }
    
    @Override
    public Map<String, User> getUsers() {
        Map<String, User> users = new LinkedHashMap<String, User>();
        users.put("1", new User("1", "张三"));
        users.put("2", new User("2", "李四"));
        return users;
    }
    
    @Override
    public void addUsers(List<User> users) {
        for (User user : users) {
            LogUtil.info("# {}", user.toString());
        }
    }
    
    @Override
    public void addUsers(Map<String, User> users) {
        for (User user : users.values()) {
            LogUtil.info("## {}", user.toString());
        }
    }
}
