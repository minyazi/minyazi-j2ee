package com.minyazi.test.webservice;

import java.util.ArrayList;
import java.util.HashMap;

public class DataTypeWebServiceImpl implements DataTypeWebService {
    @Override
    public boolean isExist(User user) {
        if (user != null && user.getUserId() != null) {
            if (user.getUserId() == 1 || user.getUserId() == 2) {
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public String getUserName(Integer userId) {
        if (userId != null) {
            if (userId == 1) {
                return "张三";
            } else if (userId == 2) {
                return "李四";
            }
        }
        
        return "";
    }
    
    @Override
    public User getUserById(Integer userId) {
        if (userId != null) {
            if (userId == 1) {
                return new User(1, "张三");
            } else if (userId == 2) {
                return new User(2, "李四");
            }
        }
        
        return null;
    }
    
    @Override
    public ArrayList<User> getUsersByName(String userName) {
        ArrayList<User> users = new ArrayList<User>();
        if (userName != null) {
            if (userName.equals("张三")) {
                users.add(new User(1, "张三"));
            } else if (userName.equals("李四")) {
                users.add(new User(2, "李四"));
            }
        }
        return users;
    }
    
    @Override
    public HashMap<Integer, User> getUsers() {
        HashMap<Integer, User> users = new HashMap<Integer, User>();
        users.put(1, new User(1, "张三"));
        users.put(2, new User(2, "李四"));
        return users;
    }
}
