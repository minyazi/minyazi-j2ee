package com.minyazi.j2ee.test.datastructure;

/**
 * 索引越界异常
 * 
 * @author minyazi
 */
public class IndexOutOfBoundsException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    public IndexOutOfBoundsException() {
        super();
    }
    
    public IndexOutOfBoundsException(String message) {
        super(message);
    }
    
    public IndexOutOfBoundsException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public IndexOutOfBoundsException(Throwable cause) {
        super(cause);
    }
}
