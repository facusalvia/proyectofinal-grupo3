package com.santander.proyectofinal.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CanceledReservationForMonthListResponseDTO {

    private List<CanceledFlightsReservationForMonthResponseDTO> canceledReservationForMonth;
}
