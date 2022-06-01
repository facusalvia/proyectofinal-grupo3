package com.santander.proyectofinal.controllers;

import com.santander.proyectofinal.dto.SuccessDTO;
import com.santander.proyectofinal.dto.request.TouristicPackageRequestDTO;
import com.santander.proyectofinal.service.TouristicPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/touristicpackage")
public class TouristicPackageController {

    @Autowired
    TouristicPackageService touristPackageService;

    @GetMapping("/")
    public String home(){
        return "estas en home tourist package";
    }

    @PostMapping("/new")
    public ResponseEntity<SuccessDTO> addTouristicPackage(@RequestBody TouristicPackageRequestDTO touristicPackageRequestDTO){
        touristPackageService.addTouristicPackage(touristicPackageRequestDTO);
        return ResponseEntity.ok().body(new SuccessDTO("Paquete Turístico dado de alta correctamente", HttpStatus.OK.value()));
    }

}
