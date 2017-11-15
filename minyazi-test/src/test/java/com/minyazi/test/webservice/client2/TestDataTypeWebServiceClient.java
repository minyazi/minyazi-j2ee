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
    }
}
