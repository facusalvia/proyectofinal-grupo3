package com.santander.proyectofinal.dto.response;

import com.santander.proyectofinal.dto.FlightCanceledReservationDTO;
import com.santander.proyectofinal.entity.FlightReservationEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CanceledFlightsReservationForMonthResponseDTO {

    private int month;
    private List<FlightCanceledReservationDTO> canceledFlightsReservation;

}
