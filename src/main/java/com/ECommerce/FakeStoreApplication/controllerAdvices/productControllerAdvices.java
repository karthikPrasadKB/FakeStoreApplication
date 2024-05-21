package com.ECommerce.FakeStoreApplication.controllerAdvices;

import com.ECommerce.FakeStoreApplication.dtos.ExceptionDto;
import com.ECommerce.FakeStoreApplication.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class productControllerAdvices {
    @ExceptionHandler(ProductNotFoundException.class)
    private ResponseEntity<ExceptionDto> handleProductNotFoundException(ProductNotFoundException productNotFoundException){
        return new ResponseEntity<>(new ExceptionDto(HttpStatus.NOT_FOUND, productNotFoundException.getMessage()),
                HttpStatus.NOT_FOUND);
    }
}
