package com.mslearning.attendancesystem.exception;

import com.mslearning.attendancesystem.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoDatafoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNoDataFoundException(NoDatafoundException ex, WebRequest request) {
    return new ErrorResponse("Data not found",request.getDescription(false));
}

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse allException(NoDatafoundException ex, WebRequest request) {
        return new ErrorResponse("Internal server error",request.getDescription(false));
    }
}
