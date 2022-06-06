package com.santander.proyectofinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTOResponseProtected {
    private Integer id;
    private String username;
    private String rol;
    private boolean accountNonLocked;
}
