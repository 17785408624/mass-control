package com.example.common.exceptiondefine;

/**
 * 绑定手机号码验证异常
 */
public class BoundPhoneException extends Exception {
    public BoundPhoneException(String message) {
        super(message);
    }

}
