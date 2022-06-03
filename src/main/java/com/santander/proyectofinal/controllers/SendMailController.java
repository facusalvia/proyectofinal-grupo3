package com.santander.proyectofinal.controllers;

import com.santander.proyectofinal.dto.FlightDTO;
import com.santander.proyectofinal.dto.SuccessDTO;
import com.santander.proyectofinal.dto.request.FlightReservationRequestDTO;
import com.santander.proyectofinal.dto.request.MailRequestDTO;
import com.santander.proyectofinal.entity.UserEntity;
import com.santander.proyectofinal.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/email")
public class SendMailController {

    @Autowired
    SendMailService sendMailService;

    @PostMapping("/send")
    public ResponseEntity<SuccessDTO> sendMail(@Valid @RequestBody MailRequestDTO  mailRequestDTO) {
        sendMailService.sendEmail(mailRequestDTO);
        return ResponseEntity.ok().body(new SuccessDTO("Send mail correct", 201));
    }
}
