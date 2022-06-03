package com.santander.proyectofinal.dto.request;

import com.santander.proyectofinal.dto.PaymentMethodDTO;
import com.santander.proyectofinal.dto.TicketDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;

@Valid
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TicketRequestDTO {
    private TicketDTO ticketDTO;
    private String userName;
    private PaymentMethodDTO paymentMethod;
}
