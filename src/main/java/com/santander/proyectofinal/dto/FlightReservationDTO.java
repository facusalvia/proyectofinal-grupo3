package com.santander.proyectofinal.dto;

import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class FlightReservationDTO {

    private LocalDate goingDate;
    private LocalDate returnDate;
    private String origin;
    private String destination;
    private String flightNumber;
    private Integer seats;
    private String seatType;
    private List<PersonDTO> people;
    private PaymentMethodDTO paymentMethod;

}
