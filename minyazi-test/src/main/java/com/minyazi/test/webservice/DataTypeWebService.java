package com.minyazi.test.webservice;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@WebService
public interface DataTypeWebService {
    boolean isExist(User user);
    
    String getUserName(String id);
    
    User getUserById(String id);
    
    List<User> getUsersByName(String name);
    
    @XmlJavaTypeAdapter(MapAdapter.class)
    Map<String, User> getUsers();
}
