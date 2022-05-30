package com.santander.proyectofinal.service;

import com.santander.proyectofinal.dto.SuccessDTO;
import com.santander.proyectofinal.dto.request.HotelRequestDTO;
import com.santander.proyectofinal.dto.response.HotelResponseDTO;
import com.santander.proyectofinal.dto.response.ListHotelResponseDto;
import com.santander.proyectofinal.entity.HotelBookingEntity;
import com.santander.proyectofinal.entity.HotelEntity;
import com.santander.proyectofinal.entity.UserEntity;
import com.santander.proyectofinal.repository.IHotelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public ListHotelResponseDto getHotels(){
        List<HotelEntity> listHotels = hotelRepository.findAll();
        return new ListHotelResponseDto(listHotels.stream().map(hotelEntity ->modelMapper.map(hotelEntity,HotelResponseDTO.class)).collect(Collectors.toList()));
    }

    //TODO: agregar excepciones customizadas
    public SuccessDTO updateHotel(String hotelCode, HotelRequestDTO hotelRequestDTO) {
        HotelEntity hotelEntity = hotelRepository.findByHotelCode(hotelCode).orElseThrow(()-> {throw new RuntimeException("Hotel inexistente");});
        Integer idHotel = hotelEntity.getId();
        hotelEntity = modelMapper.map(hotelRequestDTO, HotelEntity.class);
        hotelEntity.setId(idHotel);
        hotelRepository.save(hotelEntity);
        return new SuccessDTO("Hotel modificado correctamente",200);
    }

    public ListHotelResponseDto getHotelesWithDateFromDateToAndDestination(LocalDate from, LocalDate to, String destination) {
        List<HotelEntity> listHotels = hotelRepository.findHotelWithDateFromDateToAndDestination(from,to,destination);
        return new ListHotelResponseDto(listHotels.stream().map(hotelEntity ->modelMapper.map(hotelEntity,HotelResponseDTO.class)).collect(Collectors.toList()));
    }

    public SuccessDTO deleteHotel(String hotelCode) {
        HotelEntity hotelEntity = hotelRepository.findByHotelCode(hotelCode).orElseThrow(()-> {throw new RuntimeException("Hotel inexistente");});
        List<HotelBookingEntity> listHotelBookingEntity = hotelRepository.findIfExisteBookings(hotelEntity.getHotelCode());
        if (listHotelBookingEntity.isEmpty()){
            hotelRepository.delete(hotelEntity);
            return new SuccessDTO("Hotel dado de baja correctamente",200);
        }
        else
            throw new RuntimeException("El hotel no puede ser dado de baja ya que posee reservas vigentes");
    }
}
