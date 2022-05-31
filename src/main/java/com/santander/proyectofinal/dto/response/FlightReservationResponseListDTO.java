package com.santander.proyectofinal.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FlightReservationResponseListDTO {
    private List<FlightReservationResponseDTO> flightReservationResponseDTOList;
}
