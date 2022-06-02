package com.santander.proyectofinal.util;

import com.santander.proyectofinal.dto.FlightReservationDTO;
import com.santander.proyectofinal.dto.PaymentMethodDTO;
import com.santander.proyectofinal.dto.PersonDTO;
import com.santander.proyectofinal.dto.request.FlightReservationRequestDTO;
import com.santander.proyectofinal.entity.FlightEntity;
import com.santander.proyectofinal.entity.FlightReservationEntity;
import com.santander.proyectofinal.entity.PersonEntity;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
        return new FlightReservationEntity(1,"username",
                LocalDate.of(2022,06,05),
                LocalDate.of(2022,06,05),
                "origin",
                "destination",
                 1,
                "seatType",
                FlightEntityFactory.newFlightEntity(),
                newListPerson(),
                PaymentMethodEntityFactory.newPaymentMethodEntity(),
                false,
                LocalDate.of(2022,06,05),
                0.0);
    }
}
