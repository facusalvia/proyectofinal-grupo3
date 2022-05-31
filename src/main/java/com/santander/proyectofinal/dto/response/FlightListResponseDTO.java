package com.santander.proyectofinal.dto.response;

import com.santander.proyectofinal.dto.FlightDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FlightListResponseDTO {
    private List<FlightDTO> flightListResponseDTO;
}
