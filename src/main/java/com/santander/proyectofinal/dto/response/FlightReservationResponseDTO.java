package com.santander.proyectofinal.dto.response;

import com.santander.proyectofinal.dto.FlightReservationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FlightReservationResponseDTO {
    private String username;
    private FlightReservationDTO flightReservationDTO;
}
