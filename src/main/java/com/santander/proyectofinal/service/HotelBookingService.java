package com.santander.proyectofinal.service;

import com.santander.proyectofinal.dto.SuccessDTO;
import com.santander.proyectofinal.dto.request.HotelBookingDTORequest;
import com.santander.proyectofinal.dto.response.HotelResponseDTO;
import com.santander.proyectofinal.dto.response.ListHotelBookingResponseDTO;
import com.santander.proyectofinal.dto.response.ListHotelResponseDto;
import com.santander.proyectofinal.entity.GuestEntity;
import com.santander.proyectofinal.entity.HotelBookingEntity;
import com.santander.proyectofinal.entity.HotelEntity;
import com.santander.proyectofinal.repository.IHotelBookingRepository;
import com.santander.proyectofinal.repository.IHotelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

        hotelBookingEntity.setHotel(hotelEntity);
        // TODO: setear el id a cada guest en caso de que ya exista en la tabla pq sino el cascade genera otra entrada

        // a cada guest le seteo el hotelEntity
        List<GuestEntity> guests = hotelBookingEntity.getPeople();
        for (GuestEntity guest: guests) {
            guest.setHotelBookingEntity(List.of(hotelBookingEntity));
        }

        hotelBookingEntity = hotelBookingRepository.save(hotelBookingEntity);

        if(hotelBookingEntity.getId() == null){
            throw new RuntimeException("Error al reservar hotel");
        }

        return new SuccessDTO("Reserva de hotel dada de alta correctamente", HttpStatus.OK.value());

    }

    public ListHotelBookingResponseDTO getHotelBookings() {
        List<HotelBookingEntity> listHotelBookings = hotelBookingRepository.findAll();
        return new ListHotelBookingResponseDTO(listHotelBookings.stream().map(hotelBookingEntity
                ->modelMapper.map(hotelBookingEntity, HotelBookingDTORequest.class)).collect(Collectors.toList()));
    }
}
