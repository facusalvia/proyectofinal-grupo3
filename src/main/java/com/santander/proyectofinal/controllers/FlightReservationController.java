package com.santander.proyectofinal.controllers;

import com.santander.proyectofinal.dto.FlightDTO;
import com.santander.proyectofinal.dto.SuccessDTO;
import com.santander.proyectofinal.dto.TaskMessage;
import com.santander.proyectofinal.dto.request.FlightReservationRequestDTO;
import com.santander.proyectofinal.dto.response.FlightReservationResponseDTO;
import com.santander.proyectofinal.service.FlightReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
public class FlightReservationController {
    @Autowired
    FlightReservationService flightReservationService;

    @PostMapping("/api/v1/flight-reservation/new")
    public ResponseEntity<SuccessDTO> postFlightReservation(@Valid @RequestBody FlightReservationRequestDTO flightReservationRequestDTO) {
        flightReservationService.reservation(flightReservationRequestDTO);
        return ResponseEntity.ok().body(new SuccessDTO("Se agrego una nueva reserva", 201));
    }

    @PutMapping(value = "/api/v1/flight-reservation/edit", params = {"id"})
    public ResponseEntity<TaskMessage> updateFlight(@Valid @RequestParam(value = "id") Integer id, @RequestBody FlightReservationRequestDTO flightReservationRequestDTO) {
        flightReservationService.update(id, flightReservationRequestDTO);
        return ResponseEntity.ok().body(new TaskMessage("Se modifico correctamente", 200));
    }

}
