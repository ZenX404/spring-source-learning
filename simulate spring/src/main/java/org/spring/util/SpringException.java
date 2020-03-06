package org.spring.util;

/**
 * 自定义异常
 */
public class SpringException extends RuntimeException {
    public SpringException(String msg) {
        super(msg);
    }
}
