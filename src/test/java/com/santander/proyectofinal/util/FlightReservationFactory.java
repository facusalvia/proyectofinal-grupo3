package com.santander.proyectofinal.util;

import com.santander.proyectofinal.dto.FlightCanceledReservationDTO;
import com.santander.proyectofinal.dto.FlightReservationDTO;
import com.santander.proyectofinal.dto.PaymentMethodDTO;
import com.santander.proyectofinal.dto.PersonDTO;
import com.santander.proyectofinal.dto.request.FlightReservationRequestDTO;
import com.santander.proyectofinal.dto.response.FlightReservationResponseDTO;
import com.santander.proyectofinal.entity.FlightEntity;
import com.santander.proyectofinal.entity.FlightReservationEntity;
import com.santander.proyectofinal.entity.PersonEntity;


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
         "Solano",
         "destination",
         "TEST-FLIGHT",
                1,
         "Prestige",
                newListPersonDTO(),
                PaymentMethodEntityFactory.newPaymentMethodDTO());
    }

    public static  List<PersonEntity> newListPerson() {
        List<PersonEntity> personEntityList = new ArrayList<>();
        return personEntityList;
    }

    public static  List<PersonDTO> newListPersonDTO() {
        List<PersonDTO> personDTOList = new ArrayList<>();
        return personDTOList;
    }
    public static FlightReservationEntity newFlightReservationEntity() {
        LocalDate canceledAt = LocalDate.of(2022,06,01);
        return new FlightReservationEntity(1,"username",
                LocalDate.of(2022,06,05),
                LocalDate.of(2022,06,05),
                "origin",
                "destination",
                 1,
                "Prestige",null,
                FlightEntityFactory.newFlightEntity(),
                newListPerson(),
                PaymentMethodEntityFactory.newPaymentMethodEntity(),
                true,
                LocalDate.now(),
                0.0,canceledAt);
    }

    public static FlightReservationResponseDTO newFlightReservationResponseDTO() {
        return new FlightReservationResponseDTO("username",
                newFlightReservationDTO());

    }

    public static FlightCanceledReservationDTO newFlightCanceledReservationDTO() {
        LocalDate goingDate = LocalDate.of(2022,06,05);
        LocalDate returnDate = LocalDate.of(2022,06,05);
        LocalDate canceledAt = LocalDate.of(2022,06,01);
        return new FlightCanceledReservationDTO(goingDate,returnDate,"origin"
                ,"destination","TEST-FLIGHT",1,"Prestige",newListPersonDTO()
                ,new PaymentMethodDTO("type","number",1),canceledAt);
    }
}

