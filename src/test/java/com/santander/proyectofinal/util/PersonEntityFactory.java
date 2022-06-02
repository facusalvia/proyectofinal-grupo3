package com.santander.proyectofinal.util;

import com.santander.proyectofinal.dto.PersonDTO;
import com.santander.proyectofinal.entity.FlightReservationEntity;
import com.santander.proyectofinal.entity.HotelBookingEntity;
import com.santander.proyectofinal.entity.PersonEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonEntityFactory {
    public static PersonEntity newPersonEntity(){
        List<FlightReservationEntity> flightReservationEntities = new ArrayList<>();
        List<HotelBookingEntity> hotelBookingEntity = new ArrayList<>();
        return new PersonEntity(1,"35854790","Facundo","facu_salvia@algo.com","Salvia",
                LocalDate.of(2022,11,03),flightReservationEntities,hotelBookingEntity);
    }
    public static PersonDTO newPersonDTO(){
        return new PersonDTO("35854790","Facundo","Salvia",LocalDate.of(2022,11,03),"facu_salvia@algo.com");
    }
}
