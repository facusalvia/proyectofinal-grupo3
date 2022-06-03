package com.santander.proyectofinal.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelYearBenefitsResponseDTO {
    private Integer year;
    @JsonProperty(value = "total_income")
    private Double totalIncome;
    @JsonProperty(value = "hotel_code")
    private String hotelCode;

}
