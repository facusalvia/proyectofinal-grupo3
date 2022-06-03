package com.santander.proyectofinal.dto.response;


import com.santander.proyectofinal.dto.request.HotelBookingDTORequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ListHotelBookingResponseDTO {

    private List<HotelBookingDTORequest> hotel_bookings;
}
