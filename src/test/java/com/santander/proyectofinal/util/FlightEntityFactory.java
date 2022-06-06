package com.santander.proyectofinal.util;

import com.santander.proyectofinal.dto.FlightDTO;
import com.santander.proyectofinal.dto.request.FlightReservationRequestDTO;
import com.santander.proyectofinal.dto.response.FlightListResponseDTO;
import com.santander.proyectofinal.entity.FlightEntity;
import com.santander.proyectofinal.entity.FlightReservationEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FlightEntityFactory {

    public static FlightEntity newFlightEntity(){
        List<FlightReservationEntity> flightReservationEntityList = new ArrayList<>();
        return new FlightEntity(1,"TEST-FLIGHT", LocalDate.of(2022,06,05),
                LocalDate.of(2022,06,05),2520,"Solano","Florencio Varela",
                "Prestige",flightReservationEntityList);
    }
    public static FlightDTO newFlightDTO(){
        return new FlightDTO("TEST-FLIGHT", LocalDate.of(2022,06,05),
                LocalDate.of(2022,06,05),2520,"Solano","Florencio Varela",
                "Prestige");
    }


}
