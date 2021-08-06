package com.epam.JWTSpringSecurityApp_JavaBrains.main_app.rest;

import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.exception.ServiceException;
import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.model.Error;
import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.model.enums.ErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class ErrorHandlingController {

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error handleServiceException(ServiceException exception, HandlerMethod handlerMethod) {
        log.error("handleServiceException: message: {}, method: {}", exception.getMessage(),
                handlerMethod.getMethod(), exception);
        return new Error(exception.getMessage(), exception.getErrorType(), LocalDateTime.now());
    }
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error handleException(Exception e, HandlerMethod handlerMethod) {
        log.error("handleException: message: {}, method: {}", e.getMessage(),
                handlerMethod.getMethod(), e);
        return new Error(e.getMessage(), ErrorType.FATAL_ERROR_TYPE, LocalDateTime.now());
    }
}
