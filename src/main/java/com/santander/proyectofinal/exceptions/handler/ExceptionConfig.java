package com.santander.proyectofinal.exceptions.handler;

import com.santander.proyectofinal.dto.ErrorDTO;
import com.santander.proyectofinal.exceptions.*;
import com.santander.proyectofinal.exceptions.flightException.FlightAlreadyExistsException;
import com.santander.proyectofinal.exceptions.flightException.FlightCanNotDeleteException;
import com.santander.proyectofinal.exceptions.flightException.FlightDoesNotExistException;
import com.santander.proyectofinal.exceptions.flightException.FlightNoAvailableException;
import com.santander.proyectofinal.exceptions.hotelException.HotelAlreadyExistsException;
import com.santander.proyectofinal.exceptions.hotelException.HotelBookingDoesNotExistException;
import com.santander.proyectofinal.exceptions.hotelException.HotelCanNotDeleteException;
import com.santander.proyectofinal.exceptions.hotelException.HotelDoesNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.format.DateTimeParseException;

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
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDTO> handleValidationExceptions(ConstraintViolationException e) {
        ErrorDTO error = new ErrorDTO(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ErrorDTO> handleValidationExceptions(DateTimeParseException e){
        ErrorDTO error = new ErrorDTO("La fecha deberá ser de formato: dd/mm/yyyy");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FlightAlreadyExistsException.class)
    public ResponseEntity<ErrorDTO> handleValidationExceptions(FlightAlreadyExistsException e){
        ErrorDTO error = new ErrorDTO(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FlightNoAvailableException.class)
    public ResponseEntity<ErrorDTO> handleValidationExceptions(FlightNoAvailableException e){
        ErrorDTO error = new ErrorDTO(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FlightDoesNotExistException.class)
    public ResponseEntity<ErrorDTO> handleValidationExceptions(FlightDoesNotExistException e){
        ErrorDTO error = new ErrorDTO(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FlightCanNotDeleteException.class)
    public ResponseEntity<ErrorDTO> handleValidationExceptions(FlightCanNotDeleteException e){
        ErrorDTO error = new ErrorDTO(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity<ErrorDTO> handleValidationExceptions(UserDoesNotExistException e){
        ErrorDTO error = new ErrorDTO(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
