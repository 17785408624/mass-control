package com.example.common.exceptiondefine;

/**
 * 业务操作异常
 */
public class OperationServiceException extends Exception{
    public OperationServiceException(String message){
        super(message);
    }
}
