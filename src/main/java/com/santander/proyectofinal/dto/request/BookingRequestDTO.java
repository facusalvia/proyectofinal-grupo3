package com.santander.proyectofinal.dto.request;

import com.santander.proyectofinal.dto.GuestDTO;
import com.santander.proyectofinal.dto.PaymentMethodDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingRequestDTO {
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String destination;
    private String hotelCode;
    private Integer peopleAmount;
    private String roomType;
    private List<GuestDTO> people;
    private PaymentMethodDTO paymentMethod;
}
