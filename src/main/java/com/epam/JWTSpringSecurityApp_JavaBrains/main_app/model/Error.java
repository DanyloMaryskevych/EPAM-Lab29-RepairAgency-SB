package com.epam.JWTSpringSecurityApp_JavaBrains.main_app.model;

import com.epam.JWTSpringSecurityApp_JavaBrains.main_app.model.enums.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Error {

    private String message;
    private ErrorType errorType;
    private LocalDateTime timeStamp;
}
