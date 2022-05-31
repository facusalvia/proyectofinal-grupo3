package com.santander.proyectofinal.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.santander.proyectofinal.dto.GuestDTO;
import com.santander.proyectofinal.dto.PaymentMethodDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingRequestDTO {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dateFrom;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dateTo;
    private String destination;
    private String hotelCode;
    private Integer peopleAmount;
    private String roomType;
    private List<GuestDTO> people;
    private PaymentMethodDTO paymentMethod;
}
