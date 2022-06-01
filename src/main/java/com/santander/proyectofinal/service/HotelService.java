package com.santander.proyectofinal.service;

import com.santander.proyectofinal.dto.request.HotelRequestDTO;
import com.santander.proyectofinal.dto.response.HotelResponseDTO;
import com.santander.proyectofinal.dto.response.ListHotelResponseDto;
import com.santander.proyectofinal.entity.HotelBookingEntity;
import com.santander.proyectofinal.entity.HotelEntity;
import com.santander.proyectofinal.exceptions.hotelException.HotelAlreadyExistsException;
import com.santander.proyectofinal.exceptions.hotelException.HotelCanNotDeleteException;
import com.santander.proyectofinal.exceptions.hotelException.HotelDoesNotExistException;
import com.santander.proyectofinal.exceptions.RepositorySaveException;
import com.santander.proyectofinal.exceptions.hotelException.HotelsNoAvailableException;
import com.santander.proyectofinal.repository.IHotelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService {


    @Autowired
    IHotelRepository hotelRepository;

    ModelMapper modelMapper = new ModelMapper();

    public HotelRequestDTO addHotel(HotelRequestDTO hotelRequestDTO) {
        HotelEntity hotelEntity = modelMapper.map(hotelRequestDTO, HotelEntity.class);
        if(hotelRepository.findByHotelCode(hotelRequestDTO.getHotelCode()).isPresent()){
            throw new HotelAlreadyExistsException();
        }

        hotelEntity = hotelRepository.save(hotelEntity);
        if(hotelEntity.getId() == null){
            throw new RepositorySaveException();
        }
        return hotelRequestDTO;

    }

    public ListHotelResponseDto getHotels(){
        List<HotelEntity> listHotels = hotelRepository.findAll();
        if(listHotels.isEmpty())
            throw new HotelsNoAvailableException();
        return new ListHotelResponseDto(listHotels.stream().map(hotelEntity ->modelMapper.map(hotelEntity,HotelResponseDTO.class)).collect(Collectors.toList()));
    }

    public HotelRequestDTO updateHotel(String hotelCode, HotelRequestDTO hotelRequestDTO) {
        HotelEntity hotelEntity = hotelRepository.findByHotelCode(hotelCode).orElseThrow(HotelDoesNotExistException::new);
        Integer idHotel = hotelEntity.getId();
        hotelEntity = modelMapper.map(hotelRequestDTO, HotelEntity.class);
        hotelEntity.setId(idHotel);
        hotelRepository.save(hotelEntity);
        return hotelRequestDTO;
    }

    public ListHotelResponseDto getHotelesWithDateFromDateToAndDestination(LocalDate from, LocalDate to, String destination) {
        List<HotelEntity> listHotels = hotelRepository.findHotelWithDateFromDateToAndDestination(from,to,destination);
        if(listHotels.isEmpty())
            throw new HotelsNoAvailableException();
        return new ListHotelResponseDto(listHotels.stream().map(hotelEntity ->modelMapper.map(hotelEntity,HotelResponseDTO.class)).collect(Collectors.toList()));
    }

    public HotelRequestDTO deleteHotel(String hotelCode) {
        HotelEntity hotelEntity = hotelRepository.findByHotelCode(hotelCode).orElseThrow(HotelDoesNotExistException::new);
        List<HotelBookingEntity> listHotelBookingEntity = hotelRepository.findIfExisteBookings(hotelEntity.getHotelCode());
        if (!listHotelBookingEntity.isEmpty())
            throw new HotelCanNotDeleteException();
        hotelRepository.delete(hotelEntity);
        HotelRequestDTO hotelRequestDTO = modelMapper.map(hotelEntity, HotelRequestDTO.class);
        return hotelRequestDTO;
    }
}
