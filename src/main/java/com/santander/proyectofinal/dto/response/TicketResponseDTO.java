package com.santander.proyectofinal.dto.response;

import com.santander.proyectofinal.dto.PaymentMethodDTO;
import com.santander.proyectofinal.dto.TaskMessage;
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
public class TicketResponseDTO {
    private TicketDTO ticketDTO;
    private String userName;
    private PaymentMethodDTO paymentMethod;
    private Double amount;
    private Double interest;
    private Double total;
    private TaskMessage taskMessage;
}
