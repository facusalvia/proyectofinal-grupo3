package com.santander.proyectofinal.controllers;

import com.santander.proyectofinal.config.JwtUtils;
import com.santander.proyectofinal.dto.SuccessDTO;
import com.santander.proyectofinal.dto.request.HotelRequestDTO;
import com.santander.proyectofinal.dto.response.HotelResponseDTO;
import com.santander.proyectofinal.dto.response.ListHotelResponseDto;
import com.santander.proyectofinal.service.HotelService;
import com.santander.proyectofinal.service.UserDetailsServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/v1/hotels")
public class HotelController {

    @Autowired
    HotelService hotelService;

    @GetMapping("/")
    public String home(){
        return "Home hotels";
    }

    @PostMapping("/new")
    public ResponseEntity<HotelResponseDTO> addHotel(@RequestBody HotelRequestDTO hotelRequestDTO){
        return ResponseEntity.ok().body(hotelService.addHotel(hotelRequestDTO));
    }

    @GetMapping()
    public ResponseEntity<ListHotelResponseDto> getHotels(){
        return ResponseEntity.ok().body(hotelService.getHotels());
    }

    @PutMapping(value = "/edit", params = {"hotelCode"})
    public ResponseEntity<SuccessDTO> updateHotel(@RequestParam(value="hotelCode") String hotelCode, @RequestBody HotelRequestDTO hotelRequestDTO){
        return ResponseEntity.ok().body(hotelService.updateHotel(hotelCode,hotelRequestDTO));
    }

    @GetMapping(value = "", params = {"dateFrom","dateTo","destination"})
    public ResponseEntity<ListHotelResponseDto> getHotelesWithDateFromDateToAndDestination
            (@RequestParam(value="dateFrom") String dateFrom,
             @RequestParam(value="dateTo") String dateTo,
             @RequestParam(value="destination") String destination){


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);

        return ResponseEntity.ok().body(hotelService.getHotelesWithDateFromDateToAndDestination(from,to,destination));
    }


}
