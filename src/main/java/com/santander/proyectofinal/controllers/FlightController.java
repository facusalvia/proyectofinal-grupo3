package com.santander.proyectofinal.controllers;


import com.santander.proyectofinal.dto.FlightDTO;
import com.santander.proyectofinal.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    @GetMapping("/api/v1/flights")
    public ResponseEntity<List<FlightDTO>> getFlights() {
        List<FlightDTO> vueloDtos = flightService.getFlights();
        return new ResponseEntity<>(vueloDtos, HttpStatus.OK);
    }


    @GetMapping(value = "/api/v1/flights", params = {"fechaDesde", "fechaHasta", "origen", "destino"})
    public ResponseEntity<List<FlightDTO>> getFlightsAvailable(@RequestParam(value = "fechaDesde") @NotBlank String fechaDesde,
                                                               @RequestParam (value = "fechaHasta") @NotBlank String fechaHasta,
                                                               @RequestParam (value = "origen") @NotBlank String origen,
                                                               @RequestParam(value = "destino") @NotBlank (message = "el campo destino no puede estar en blanco") String destino) {

        // parseo
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaDesdeParseo = LocalDate.parse(fechaDesde, formatter);
        LocalDate fechaHastaParseo = LocalDate.parse(fechaHasta, formatter);

        //prueba

        List<FlightDTO> flightDTOs = flightService.getFlightsByDate(origen, destino, fechaDesdeParseo, fechaHastaParseo);
        return new ResponseEntity<>(flightDTOs, HttpStatus.OK);
    }


    @PutMapping("/api/v1/flights")
    public ResponseEntity<FlightDTO> actualizacionVuelo(@Valid @RequestBody FlightDTO flightDTO) {
        FlightDTO respuesta = flightService.update(flightDTO);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

}
