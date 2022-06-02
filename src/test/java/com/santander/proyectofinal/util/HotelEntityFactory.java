package com.santander.proyectofinal.util;

import com.santander.proyectofinal.dto.request.HotelRequestDTO;
import com.santander.proyectofinal.dto.response.HotelResponseDTO;
import com.santander.proyectofinal.entity.HotelBookingEntity;
import com.santander.proyectofinal.entity.HotelEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HotelEntityFactory {

    public static HotelEntity newHotelEntity(){
        List<HotelBookingEntity> listBookings=new ArrayList<>();
        return new HotelEntity(1,"NUEVO-001","Hotel Nuevo",
                "La Plata","Double",6200.0, LocalDate.of(2022,06,05),
                LocalDate.of(2022,06,10),false,listBookings);
    }

    public static HotelRequestDTO newHotelRequestDTO(){
        return new HotelRequestDTO("NUEVO-001","Hotel Nuevo",
                "La Plata","Double",6200.0, LocalDate.of(2022,06,05),
                LocalDate.of(2022,06,10),false);
    }
    public static HotelResponseDTO newHotelResponseDTO(){
        return new HotelResponseDTO(1,"NUEVO-001","Hotel Nuevo",
                "La Plata","Double",6200.0, LocalDate.of(2022,06,05),
                LocalDate.of(2022,06,10),false);
    }



}
