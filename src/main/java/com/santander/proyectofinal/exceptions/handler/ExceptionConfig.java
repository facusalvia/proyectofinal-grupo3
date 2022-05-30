package com.santander.proyectofinal.exceptions.handler;

import com.santander.proyectofinal.dto.ErrorDTO;
import com.santander.proyectofinal.exceptions.HotelAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionConfig {
    @ExceptionHandler(HotelAlreadyExistsException.class)
    public ResponseEntity<ErrorDTO> handlerException(HotelAlreadyExistsException hotelAlreadyExistsException){
        ErrorDTO errorDto = new ErrorDTO(hotelAlreadyExistsException.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

}
