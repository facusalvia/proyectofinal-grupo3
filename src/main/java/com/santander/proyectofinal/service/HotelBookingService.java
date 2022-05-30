package com.santander.proyectofinal.service;

import com.santander.proyectofinal.dto.SuccessDTO;
import com.santander.proyectofinal.dto.request.HotelBookingDTORequest;
import com.santander.proyectofinal.entity.HotelBookingEntity;
import com.santander.proyectofinal.entity.HotelEntity;
import com.santander.proyectofinal.repository.IHotelBookingRepository;
import com.santander.proyectofinal.repository.IHotelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class HotelBookingService {

    @Autowired
    IHotelBookingRepository hotelBookingRepository;

    @Autowired
    IHotelRepository hotelRepository;

    ModelMapper modelMapper = new ModelMapper();

    public SuccessDTO addBooking(HotelBookingDTORequest hotelBookingDTORequest) {
        // verifico que exista el hotel
        HotelEntity hotelEntity = hotelRepository.findByHotelCode(hotelBookingDTORequest.getBooking().getHotelCode()).orElseThrow();

        HotelBookingEntity hotelBookingEntity = modelMapper.map(hotelBookingDTORequest.getBooking(), HotelBookingEntity.class);

        hotelBookingEntity = hotelBookingRepository.save(hotelBookingEntity);

        if(hotelBookingEntity.getId() == null){
            throw new RuntimeException("Error al reservar hotel");
        }

        return new SuccessDTO("Reserva de hotel dada de alta correctamente", HttpStatus.OK.value());

    }
}
