package com.santander.proyectofinal.controllers;

import com.santander.proyectofinal.dto.SuccessDTO;
import com.santander.proyectofinal.dto.request.HotelBookingDTORequest;
import com.santander.proyectofinal.service.HotelBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hotel-booking")
public class HotelBookingController {

    @Autowired
    HotelBookingService hotelBookingService;

    @PostMapping("/new")
    public ResponseEntity<SuccessDTO> addHotelBooking(@RequestBody HotelBookingDTORequest hotelBookingDTORequest){
        return ResponseEntity.ok().body(hotelBookingService.addBooking(hotelBookingDTORequest));
    }
}
