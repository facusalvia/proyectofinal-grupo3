package com.santander.proyectofinal.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelRequestDTO {
    private String hotelCode;
    private String name;
    private String place;
    private String roomType;
    private Double roomPrice;
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate disponibilityDateFrom;
    private LocalDate disponibilityDateTo;
    private Boolean isBooking;
}
