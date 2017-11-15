package com.minyazi.test.webservice.client2;

import java.util.List;

import org.junit.Test;

import com.minyazi.core.util.LogUtil;

public class TestDataTypeWebServiceClient {
    @Test
    public void test() {
        DataTypeWebServiceImplService factory = new DataTypeWebServiceImplService();
        DataTypeWebService service = factory.getDataTypeWebServiceImplPort();
        
        User user = new User();
        user.setUserId(1);
        
        LogUtil.info(service.isExist(user) + "");
        
        LogUtil.info(service.getUserName(user.getUserId()));
        
        user = service.getUserById(user.getUserId());
        LogUtil.info("{} : {}", user.getUserId(), user.getUserName());
        
        List<User> users = service.getUsersByName(user.getUserName());
        for (User _user : users) {
            LogUtil.info("{} : {}", _user.getUserId(), _user.getUserName());
        }
        
        HashMap _users = service.getUsers();
        LogUtil.info(_users.toString());
    }
}
