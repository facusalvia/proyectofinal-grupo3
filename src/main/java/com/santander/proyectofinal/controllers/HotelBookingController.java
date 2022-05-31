package com.santander.proyectofinal.controllers;

import com.santander.proyectofinal.dto.SuccessDTO;
import com.santander.proyectofinal.dto.request.HotelBookingDTORequest;
import com.santander.proyectofinal.dto.response.ListHotelBookingResponseDTO;
import com.santander.proyectofinal.dto.response.ListHotelResponseDto;
import com.santander.proyectofinal.service.HotelBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hotel-booking")
public class HotelBookingController {

    @Autowired
    HotelBookingService hotelBookingService;

    @PostMapping("/new")
    public ResponseEntity<SuccessDTO> addHotelBooking(@RequestBody HotelBookingDTORequest hotelBookingDTORequest){
        return ResponseEntity.ok().body(hotelBookingService.addBooking(hotelBookingDTORequest));
    }

    @PutMapping(value = "/edit", params = {"id"})
    public ResponseEntity<SuccessDTO> getHotelBookings(@RequestParam(value = "id") Integer bookingId, @RequestBody HotelBookingDTORequest hotelBookingDTORequest){
        return ResponseEntity.ok().body(hotelBookingService.updateHotelBooking(bookingId, hotelBookingDTORequest));
    }

    @DeleteMapping(value ="/delete", params= {"idReservation"})
    public ResponseEntity<SuccessDTO> deleteHotelBooking(@RequestParam(value="idReservation") Integer idReservation){
        return ResponseEntity.ok().body(hotelBookingService.deleteHotelBooking(idReservation));
    }

    @GetMapping("/")
    public ResponseEntity<ListHotelBookingResponseDTO> getHotelBookings(){
        return ResponseEntity.ok().body(hotelBookingService.getHotelBookings());
    }
}
