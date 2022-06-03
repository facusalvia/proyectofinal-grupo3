package com.santander.proyectofinal.controllers;


import com.santander.proyectofinal.dto.FlightDTO;
import com.santander.proyectofinal.dto.SuccessDTO;
import com.santander.proyectofinal.dto.TaskMessage;
import com.santander.proyectofinal.dto.response.FlightListResponseDTO;
import com.santander.proyectofinal.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@RestController
@Validated
public class FlightController {
    @Autowired
    FlightService flightService;

    @PostMapping("api/v1/flights/new")
    public ResponseEntity<TaskMessage> addFlight(@Valid @RequestBody FlightDTO flightDTO) {
        flightService.add(flightDTO);
        return ResponseEntity.ok().body(new TaskMessage("Se agrego un nuevo vuelo", 201));
    }

    @GetMapping("/api/v1/flights")
    public ResponseEntity<FlightListResponseDTO> getFlights() {
        FlightListResponseDTO vueloDtos = flightService.getFlights();
        return new ResponseEntity<>(vueloDtos, HttpStatus.OK);
    }


    @GetMapping(value = "/api/v1/flights", params = {"dateFrom", "dateTo", "origin", "destiny"})
    public ResponseEntity<FlightListResponseDTO> getFlightsAvailable(@RequestParam(value = "dateFrom") String dateFrom,
                                                                     @RequestParam(value = "dateTo") String dateTo,
                                                                     @RequestParam(value = "origin") @NotBlank String origin,
                                                                     @RequestParam(value = "destiny") @NotBlank(message = "el campo destino no puede estar en blanco") String destiny) {

        FlightListResponseDTO flightDTOs = flightService.getFlightsByDateAndOriginAndDestiny(origin, destiny, dateFrom, dateTo);
        return new ResponseEntity<>(flightDTOs, HttpStatus.OK);
    }


    @PutMapping(value = "/api/v1/flights/edit", params = {"flightNumber"})
    public ResponseEntity<TaskMessage> updateFlight(@Valid @RequestParam(value = "flightNumber") String flightNumber, @RequestBody FlightDTO flightDTO) {
        flightService.update(flightNumber, flightDTO);
        return ResponseEntity.ok().body(new TaskMessage("Se modifico correctamente", 200));
    }

    @DeleteMapping(value ="/api/v1/flight/delete", params= {"flightNumber"})
    public ResponseEntity<SuccessDTO> deleteFlightReservation(@RequestParam(value="flightNumber") String flightNumber){
        flightService.deleteFlight(flightNumber);
        return ResponseEntity.ok().body(new SuccessDTO( "Vuelo dada de baja correctamente" , 200));
    }

}
