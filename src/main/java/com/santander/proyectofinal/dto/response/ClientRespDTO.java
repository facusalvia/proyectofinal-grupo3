package com.santander.proyectofinal.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientRespDTO {
    private Integer clientId;
    private String name;
    private String lastname;
}
