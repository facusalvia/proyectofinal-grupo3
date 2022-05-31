package com.santander.proyectofinal.exceptions.handler;

import com.santander.proyectofinal.dto.ErrorDTO;
import com.santander.proyectofinal.exceptions.*;
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

    @ExceptionHandler(HotelDoesNotExistException.class)
    public ResponseEntity<ErrorDTO> handlerException(HotelDoesNotExistException hotelDoesNotExistException){
        ErrorDTO errorDto = new ErrorDTO(hotelDoesNotExistException.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RepositorySaveException.class)
    public ResponseEntity<ErrorDTO> handlerException(RepositorySaveException repositorySaveException){
        ErrorDTO errorDto = new ErrorDTO(repositorySaveException.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HotelCanNotDeleteException.class)
    public ResponseEntity<ErrorDTO> handlerException(HotelCanNotDeleteException hotelCanNotDeleteException){
        ErrorDTO errorDto = new ErrorDTO(hotelCanNotDeleteException.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HotelBookingDoesNotExistException.class)
    public ResponseEntity<ErrorDTO> handlerException(HotelBookingDoesNotExistException hotelBookingDoesNotExistException){
        ErrorDTO errorDto = new ErrorDTO(hotelBookingDoesNotExistException.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

}
