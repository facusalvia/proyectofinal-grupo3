package com.santander.proyectofinal.dto;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Valid
public class TicketDTO {
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate to;
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate from;
    @NotBlank(message = "el campo origen no puede estar en blanco")
    @NotEmpty(message = "el campo origen no debería estar vacio")
    private String origin;
    @NotBlank(message = "el campo destino no puede estar en blanco")
    @NotEmpty(message = "el campo destino no debería estar vacio")
    private String destiny;
    @NotBlank(message = "el campo codigoVuelo no puede estar en blanco")
    @NotEmpty(message = "el campo codigoVuelo no debería estar vacio")
    private String codeFlight;
    @Digits(integer=2,fraction=0)
    @Min(1)
    private int seat;
    @NotBlank(message = "el campo tipoAsiento no puede estar en blanco")
    @NotEmpty(message = "el campo tipoAsiento no debería estar vacio")
    private String seatType;
    @Size(min = 1,message = "Debe ingresar al menos una persona")
    private List<@Valid PersonDTO> people;


}
