package com.santander.proyectofinal.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ListHotelResponseDto {

    private List<HotelResponseDTO> hotels;
}
