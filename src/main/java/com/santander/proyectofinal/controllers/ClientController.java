package com.santander.proyectofinal.controllers;

import com.santander.proyectofinal.dto.response.TopClientsResponseDTO;
import com.santander.proyectofinal.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping(value = "/top3", params = {"year"})
    public ResponseEntity<TopClientsResponseDTO> getTopClients(@RequestParam(value = "year") Integer year){

        return ResponseEntity.ok().body(clientService.getTopClients(year, 3));
    }
}
