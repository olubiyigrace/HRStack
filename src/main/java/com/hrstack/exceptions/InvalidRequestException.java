package com.hrstack.exceptions;

public class InvalidRequestException extends BusinessException {
    public InvalidRequestException(String message) {
        super(message);
    }
}
