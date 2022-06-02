package com.santander.proyectofinal.dto.response;

import com.santander.proyectofinal.dto.FlightReservationDTO;
import lombok.*;

import javax.persistence.Entity;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class FlightReservationResponseDTO {
    private String username;
    private FlightReservationDTO flightEntity;
}
