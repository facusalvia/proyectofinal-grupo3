package com.santander.proyectofinal.util;

import com.santander.proyectofinal.dto.FlightReservationDTO;
import com.santander.proyectofinal.dto.PaymentMethodDTO;
import com.santander.proyectofinal.dto.PersonDTO;
import com.santander.proyectofinal.dto.request.FlightReservationRequestDTO;
import com.santander.proyectofinal.entity.FlightEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FlightReservationFactory {


    public static FlightReservationRequestDTO newFlightReservationRequestDTO() {
        return new FlightReservationRequestDTO("username",
                newFlightReservationDTO());
    }

    public static FlightReservationDTO newFlightReservationDTO() {
        return new FlightReservationDTO( LocalDate.of(2022,06,05),
        LocalDate.of(2022,06,05),
         "origin",
         "destination",
         "flightNumber",
                1,
         "seatType",
                newListPerson(),
                newPaymentMethod());
    }

    public static  List<PersonDTO> newListPerson() {
        List<PersonDTO> personDTOList = new ArrayList<>();
        return personDTOList;
    }
    public static  PaymentMethodDTO newPaymentMethod() {
        return new  PaymentMethodDTO();
    }
}
