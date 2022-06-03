package com.santander.proyectofinal.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientResponseDTO {
    @JsonProperty(value = "top_number")
    private Integer topNumber;
    @JsonProperty(value = "booking_quantity")
    private Integer bookingQuantity;
    @JsonProperty(value = "total_amount")
    private Double totalAmount;
    @JsonProperty(value = "client_id")
    private Integer clientId;
    private String name;
    private String lastname;
}
