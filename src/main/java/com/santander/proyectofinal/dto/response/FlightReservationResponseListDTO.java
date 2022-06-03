package com.santander.proyectofinal.dto.response;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FlightReservationResponseListDTO {
    private List<FlightReservationResponseDTO> flightReservations;
}
