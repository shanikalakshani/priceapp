package com.x.priceapp.exception;

import com.x.priceapp.response.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleGeneralException(Exception ex, WebRequest request) {
        String message = "Unexpected server error";
        return new ResponseEntity<>(ErrorResponse.builder().errorCode(HttpStatus.INTERNAL_SERVER_ERROR.toString()).errorMessage(message).build(),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex, WebRequest request) {
        return new ResponseEntity<>(ErrorResponse.builder().errorCode(HttpStatus.BAD_REQUEST.toString()).errorMessage(ex.getMessage()).build(),
                HttpStatus.BAD_REQUEST);
    }

}
