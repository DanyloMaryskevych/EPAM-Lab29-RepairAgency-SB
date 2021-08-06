package com.epam.JWTSpringSecurityApp_JavaBrains.main_app.exception;

import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.model.enums.ErrorType;

public class OrderNotFoundException extends ServiceException{

    public static final String DEFAULT_MESSAGE = "Order not found";

    public OrderNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
