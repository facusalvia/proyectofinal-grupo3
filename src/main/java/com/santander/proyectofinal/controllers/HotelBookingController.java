package com.santander.proyectofinal.controllers;

import com.santander.proyectofinal.dto.SuccessDTO;
import com.santander.proyectofinal.dto.request.HotelBookingDTORequest;
import com.santander.proyectofinal.dto.response.ListHotelBookingResponseDTO;
import com.santander.proyectofinal.service.HotelBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hotel-booking")
public class HotelBookingController {

    @Autowired
    HotelBookingService hotelBookingService;

    @PostMapping("/new")
    public ResponseEntity<SuccessDTO> addHotelBooking(@RequestBody HotelBookingDTORequest hotelBookingDTORequest){
        hotelBookingService.addBooking(hotelBookingDTORequest);
        return ResponseEntity.ok().body(new SuccessDTO("Reserva de hotel dada de alta correctamente",200));
    }

    @PutMapping(value = "/edit", params = {"id"})
    public ResponseEntity<SuccessDTO> updateHotelBookings(@RequestParam(value = "id") Integer bookingId, @RequestBody HotelBookingDTORequest hotelBookingDTORequest){
        hotelBookingService.updateHotelBooking(bookingId, hotelBookingDTORequest);
        return ResponseEntity.ok().body(new SuccessDTO("La reserva ha sido modificada correctamente", HttpStatus.OK.value()));
    }

    @DeleteMapping(value ="/delete", params= {"idReservation"})
    public ResponseEntity<SuccessDTO> deleteHotelBooking(@RequestParam(value="idReservation") Integer idReservation){
        hotelBookingService.deleteHotelBooking(idReservation);
        return ResponseEntity.ok().body(new SuccessDTO("La reserva ha sido eliminada correctamente",200));
    }

    @GetMapping("/")
    public ResponseEntity<ListHotelBookingResponseDTO> getHotelBookings(){
        return ResponseEntity.ok().body(hotelBookingService.getHotelBookings());
    }
}
