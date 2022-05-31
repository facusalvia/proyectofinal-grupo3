package com.santander.proyectofinal.dto.request;

import com.santander.proyectofinal.dto.FlightReservationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FlightReservationRequestDTO {
    private String username;
    private FlightReservationDTO flightReservationDTO;
}

