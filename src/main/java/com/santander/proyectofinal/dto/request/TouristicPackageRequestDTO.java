package com.santander.proyectofinal.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TouristicPackageRequestDTO {
    private Integer packageNumber;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonProperty(value = "creation_date")
    private LocalDate creationDate;
    @JsonProperty(value = "client_id")
    private Integer clientId;
    private List<Integer> bookings;
    private List<Integer> reservations;
}
