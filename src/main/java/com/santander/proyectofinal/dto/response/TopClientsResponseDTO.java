package com.santander.proyectofinal.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopClientsResponseDTO {
    private Integer year;
    List<ClientResponseDTO> clients;
}
