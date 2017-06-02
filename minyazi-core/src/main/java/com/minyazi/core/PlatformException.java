package com.minyazi.core;

/**
 * 平台异常
 * 
 * @author minyazi
 */
public class PlatformException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    public PlatformException() {
        super();
    }
    
    public PlatformException(String message) {
        super(message);
    }
    
    public PlatformException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public PlatformException(Throwable cause) {
        super(cause);
    }
}
