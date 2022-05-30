package com.santander.proyectofinal.service;

import com.santander.proyectofinal.dto.request.HotelRequestDTO;
import com.santander.proyectofinal.dto.response.HotelResponseDTO;
import com.santander.proyectofinal.entity.HotelEntity;
import com.santander.proyectofinal.repository.IHotelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService {

    @Autowired
    IHotelRepository hotelRepository;

    ModelMapper modelMapper = new ModelMapper();

    public HotelResponseDTO addHotel(HotelRequestDTO hotelRequestDTO) {
        HotelEntity hotelEntity = modelMapper.map(hotelRequestDTO, HotelEntity.class);

        // TODO: validar que no existe un hotel con mismo codigo de hotel

        hotelEntity = hotelRepository.save(hotelEntity);

        if(hotelEntity.getId() == null){
            throw new RuntimeException("Error al agregar hotel");
        }

        return modelMapper.map(hotelEntity, HotelResponseDTO.class);
    }
}
