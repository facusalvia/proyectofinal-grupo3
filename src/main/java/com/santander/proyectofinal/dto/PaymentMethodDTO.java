package com.santander.proyectofinal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Valid
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PaymentMethodDTO {
    @NotBlank(message = "el campo tipo no puede estar en blanco")
    @NotEmpty(message = "el campo tipo no debería estar vacio")
    private String type;
    @NotBlank(message = "el campo numeroTarjeta no puede estar en blanco")
    @NotEmpty(message = "el campo numeroTarjeta no debería estar vacio")
    private String cardType;
    @Min(value = 1, message="La cantidad de cuotas debe ser q como minimo")
    private Integer installments;
}
