package com.minyazi.j2ee.service.webservice;

import com.minyazi.j2ee.service.ServiceException;

/**
 * WebService异常
 * 
 * @author minyazi
 */
public class WebServiceException extends ServiceException {
    private static final long serialVersionUID = 1L;
    
    public WebServiceException() {
        super();
    }
    
    public WebServiceException(String message) {
        super(message);
    }
    
    public WebServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public WebServiceException(Throwable cause) {
        super(cause);
    }
}
