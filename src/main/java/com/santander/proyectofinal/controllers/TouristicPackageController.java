package com.santander.proyectofinal.controllers;

import com.santander.proyectofinal.dto.SuccessDTO;
import com.santander.proyectofinal.dto.request.TouristicPackageRequestDTO;
import com.santander.proyectofinal.dto.response.ListHotelBookingResponseDTO;
import com.santander.proyectofinal.dto.response.ListTouristicPackageResponseDTO;
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

    @PostMapping("/new")
    public ResponseEntity<SuccessDTO> addTouristicPackage(@RequestBody TouristicPackageRequestDTO touristicPackageRequestDTO){
        touristPackageService.addTouristicPackage(touristicPackageRequestDTO);
        return ResponseEntity.ok().body(new SuccessDTO("Paquete Turístico dado de alta correctamente", HttpStatus.OK.value()));
    }

    @GetMapping("/")
    public ResponseEntity<ListTouristicPackageResponseDTO> getTouristicPackages(){
        return ResponseEntity.ok().body(touristPackageService.getTouristicPackages());
    }

    @DeleteMapping(value = "/delete", params = "packageNumber")
    public ResponseEntity<SuccessDTO> deleteTouristicPackage(@RequestParam(value = "packageNumber") Integer packageNumber){
        touristPackageService.deleteTouristicPackage(packageNumber);
        return ResponseEntity.ok().body(new SuccessDTO("Paquete Turístico dado de baja correctamente", HttpStatus.OK.value()));
    }

}
