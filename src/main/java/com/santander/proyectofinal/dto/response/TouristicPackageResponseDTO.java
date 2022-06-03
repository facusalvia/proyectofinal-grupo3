package com.santander.proyectofinal.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TouristicPackageResponseDTO {
    private TouristicPackageInfoResponseDTO touristicPackageInfoResponseDTO;
    private List<Integer> bookings;
    private List<Integer> reservations;
}
