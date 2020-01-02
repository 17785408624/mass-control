package com.example.common.exceptiondefine;

/**
 * 邀请审核业务异常
 */
public class AuditOperationServiceException extends OperationServiceException{
    public AuditOperationServiceException(String message) {
        super(message);
    }
}
