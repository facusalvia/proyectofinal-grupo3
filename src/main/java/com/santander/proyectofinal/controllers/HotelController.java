package com.santander.proyectofinal.controllers;

import com.santander.proyectofinal.dto.SuccessDTO;
import com.santander.proyectofinal.dto.TaskMessage;
import com.santander.proyectofinal.dto.request.HotelRequestDTO;
import com.santander.proyectofinal.dto.response.ListHotelResponseDto;
import com.santander.proyectofinal.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    public ResponseEntity<SuccessDTO> addHotel(@RequestBody HotelRequestDTO hotelRequestDTO){
        hotelService.addHotel(hotelRequestDTO);
        return ResponseEntity.ok().body(new SuccessDTO("Se agrego un nuevo hotel", 201));

    }

    @GetMapping()
    public ResponseEntity<ListHotelResponseDto> getHotels(){
        return ResponseEntity.ok().body(hotelService.getHotels());
    }

    @PutMapping(value = "/edit", params = {"hotelCode"})
    public ResponseEntity<SuccessDTO> updateHotel(@RequestParam(value="hotelCode") String hotelCode, @RequestBody HotelRequestDTO hotelRequestDTO){
        hotelService.updateHotel(hotelCode,hotelRequestDTO);
        return ResponseEntity.ok().body(new SuccessDTO("Se modifico hotel correctamente",200));
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

    @DeleteMapping(value ="/delete", params= {"hotelCode"})
    public ResponseEntity<SuccessDTO> deleteHotel(@RequestParam(value="hotelCode") String hotelCode){
        hotelService.deleteHotel(hotelCode);
        return ResponseEntity.ok().body(new SuccessDTO("Se eliminó el hotel correctamente",200));
    }


}
