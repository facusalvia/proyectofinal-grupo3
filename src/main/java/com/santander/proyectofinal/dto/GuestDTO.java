package com.santander.proyectofinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuestDTO {
    private String dni;
    private String name;
    private String lastname;
    private LocalDate birthDate;
    private String mail;
}
