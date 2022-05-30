package com.santander.proyectofinal.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelBookingDTORequest {
    private String username;
    private BookingRequestDTO booking;

}
