package com.minyazi.j2ee.dao;

/**
 * DAO异常
 * 
 * @author minyazi
 */
public class DaoException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    public DaoException() {
        super();
    }
    
    public DaoException(String message) {
        super(message);
    }
    
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public DaoException(Throwable cause) {
        super(cause);
    }
}
