package com.minyazi.test.webservice;

import java.util.ArrayList;
import java.util.HashMap;

import javax.jws.WebService;

@WebService
public interface DataTypeWebService {
    boolean isExist(User user);
    
    String getUserName(Integer userId);
    
    User getUserById(Integer userId);
    
    ArrayList<User> getUsersByName(String userName);
    
    HashMap<Integer, User> getUsers();
}
