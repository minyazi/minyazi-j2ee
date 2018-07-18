package com.minyazi.j2ee.test.webservice.client2;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.minyazi.j2ee.core.util.LogUtil;
import com.minyazi.j2ee.test.webservice.client2.DataTypeWebService;
import com.minyazi.j2ee.test.webservice.client2.DataTypeWebServiceImplService;
import com.minyazi.j2ee.test.webservice.client2.MapConvertor;
import com.minyazi.j2ee.test.webservice.client2.MapEntry;
import com.minyazi.j2ee.test.webservice.client2.User;

public class TestDataTypeWebServiceClient {
    @Test
    public void test() {
        DataTypeWebServiceImplService factory = new DataTypeWebServiceImplService();
        DataTypeWebService service = factory.getDataTypeWebServiceImplPort();
        
        User user = new User();
        user.setId("1");
        
        LogUtil.info(service.isExist(user) + "");
        
        LogUtil.info(service.getUserName(user.getId()));
        
        user = service.getUserById(user.getId());
        LogUtil.info("{} # {}", user.getId(), user.getName());
        
        List<User> users = service.getUsersByName(user.getName());
        for (User _user : users) {
            LogUtil.info("{} ## {}", _user.getId(), _user.getName());
        }
        
        for (MapEntry _user : service.getUsers().getEntries()) {
            LogUtil.info("{} ### {}", _user.getValue().getId(), _user.getValue().getName());
        }
        
        users = new ArrayList<User>();
        users.add(service.getUserById("1"));
        users.add(service.getUserById("2"));
        service.addUsers(users);
        
        MapConvertor _users = new MapConvertor();
        List<MapEntry> entries = _users.getEntries();
        MapEntry entry = new MapEntry();
        entry.setKey("1");
        entry.setValue(service.getUserById("1"));
        entries.add(entry);
        service.addUsers2(_users);
    }
}
