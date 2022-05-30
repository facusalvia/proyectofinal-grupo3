package com.santander.proyectofinal.controllers;


import com.santander.proyectofinal.dto.FlightDTO;
import com.santander.proyectofinal.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
public class FlightController {
    @Autowired
    FlightService flightService;

    @PostMapping("api/v1/flights/new")
    public ResponseEntity<FlightDTO> addFlight (@Valid @RequestBody FlightDTO flightDTO) {
        FlightDTO response = flightService.add(flightDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
