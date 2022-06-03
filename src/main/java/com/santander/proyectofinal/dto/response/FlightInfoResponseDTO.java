package com.santander.proyectofinal.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FlightInfoResponseDTO {
    private Integer reservationsQty;
    private Integer totalFlightIncome;
}
