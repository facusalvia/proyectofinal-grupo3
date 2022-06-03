package com.santander.proyectofinal.dto.response;

import com.santander.proyectofinal.dto.FlightDTO;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FlightListResponseDTO {
    private List<FlightDTO> flights;
}
