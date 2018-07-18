package com.minyazi.j2ee.test.webservice.restful;

public class CustomerWebServiceImpl implements CustomerWebService {
    @Override
    public Customer getCustomerById(String id) {
        if (id != null) {
            if (id.equals("1")) {
                return new Customer("1", "zhangsan");
            } else if (id.equals("2")) {
                return new Customer("2", "lisi");
            }
        }
        
        return null;
    }
    
    @Override
    public Customer getCustomerByName(String name) {
        if (name != null) {
            if (name.equals("zhangsan")) {
                return new Customer("1", "zhangsan");
            } else if (name.equals("lisi")) {
                return new Customer("2", "lisi");
            }
        }
        
        return null;
    }
}
