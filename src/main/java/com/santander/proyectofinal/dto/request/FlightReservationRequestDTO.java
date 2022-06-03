package com.santander.proyectofinal.dto.request;

import com.santander.proyectofinal.dto.FlightReservationDTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FlightReservationRequestDTO {
    private String username;
    private FlightReservationDTO flightReservationDTO;
}

