package com.santander.proyectofinal.exceptions.handler;

import com.santander.proyectofinal.dto.ErrorDTO;
import com.santander.proyectofinal.exceptions.*;
import com.santander.proyectofinal.exceptions.flightException.*;
import com.santander.proyectofinal.exceptions.hotelException.*;
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

    @ExceptionHandler(FlightReservationDoesNotExistException.class)
    public ResponseEntity<ErrorDTO> handleValidationExceptions(FlightReservationDoesNotExistException e) {
        ErrorDTO error = new ErrorDTO(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PackageDoesNotExistException.class)
    public ResponseEntity<ErrorDTO> handleValidationExceptions(PackageDoesNotExistException e) {
        ErrorDTO error = new ErrorDTO(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CountPackageDistintTwoException.class)
    public ResponseEntity<ErrorDTO> handleValidationExceptions(CountPackageDistintTwoException e) {
        ErrorDTO error = new ErrorDTO(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HotelBookingCanNotDeleteException.class)
    public ResponseEntity<ErrorDTO> handlerException(HotelBookingCanNotDeleteException hotelBookingCanNotDeleteException){
        ErrorDTO errorDto = new ErrorDTO(hotelBookingCanNotDeleteException.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FlightReservationCanNotDeleteException.class)
    public ResponseEntity<ErrorDTO> handlerException(FlightReservationCanNotDeleteException flightReservationCanNotDeleteException){
        ErrorDTO errorDto = new ErrorDTO(flightReservationCanNotDeleteException.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PaymentMethodDebitCanNotMoreThanOneDueException.class)
    public ResponseEntity<ErrorDTO> handlerException(PaymentMethodDebitCanNotMoreThanOneDueException paymentMethodDebitCanNotMoreThanOneDue){
        ErrorDTO errorDto = new ErrorDTO(paymentMethodDebitCanNotMoreThanOneDue.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TouristicPackageAlreadyExistsException.class)
    public ResponseEntity<ErrorDTO> handlerException(TouristicPackageAlreadyExistsException touristicPackageAlreadyExistsException){
        ErrorDTO errorDto = new ErrorDTO(touristicPackageAlreadyExistsException.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookingPeriodOutsideHotelDisponibilityPeriodException.class)
    public ResponseEntity<ErrorDTO> handlerException(BookingPeriodOutsideHotelDisponibilityPeriodException bookingPeriodOutsideHotelDisponibilityPeriod){
        ErrorDTO errorDto = new ErrorDTO(bookingPeriodOutsideHotelDisponibilityPeriod.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReservationDatesDoNotMatchFlightDatesException.class)
    public ResponseEntity<ErrorDTO> handlerException(ReservationDatesDoNotMatchFlightDatesException reservationDatesDoNotMatchFlightDatesException){
        ErrorDTO errorDto = new ErrorDTO(reservationDatesDoNotMatchFlightDatesException.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

}
