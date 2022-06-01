package com.santander.proyectofinal.controllers;

import com.santander.proyectofinal.service.TouristicPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/touristicpackage")
public class TouristicPackageController {

    @Autowired
    TouristicPackageService touristPackageService;

    @GetMapping("/")
    public String home(){
        return "estas en home tourist package";
    }

}
