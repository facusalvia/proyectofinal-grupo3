package com.santander.proyectofinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Valid
@Validated
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    @NotBlank(message = "El campo username de usuario no puede estar en blanco")
    private String username;
    @NotBlank(message = "El campo password de usuario no puede estar en blanco")
    private String password;
    @NotBlank(message = "El campo rol de usuario no puede estar en blanco")
    private String rol;
}
