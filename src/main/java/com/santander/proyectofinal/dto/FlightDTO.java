package com.santander.proyectofinal.dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Valid
@Validated
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class FlightDTO {
    @NotBlank(message = "El campo numero de vuelo no puede estar en blanco")
    @NotEmpty
    private String flightNumber;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")

    private LocalDate dateFrom;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dateTo;
    @DecimalMin(value="1.0",message = "Ingrese un valor superior ")
    private double pricePerPerson;
    @NotBlank(message = "el campo origen no puede estar en blanco")
    @NotEmpty(message = "el campo origen no debería estar vacio")
    private String origin;
    @NotBlank(message = "el campo destino no puede estar en blanco")
    @NotEmpty(message = "el campo destino no debería estar vacio")
    private String destiny;
    @NotBlank(message = "el campo tipoAsiento no puede estar en blanco")
    @NotEmpty(message = "el campo tipoAsiento no debería estar vacio")
    private String seatType;
}
