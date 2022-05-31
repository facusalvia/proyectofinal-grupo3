package com.santander.proyectofinal.service;

import com.santander.proyectofinal.dto.FlightDTO;
import com.santander.proyectofinal.dto.response.FlightListResponseDTO;
import com.santander.proyectofinal.dto.response.FlightReservationResponseDTO;
import com.santander.proyectofinal.entity.FlightEntity;
import com.santander.proyectofinal.entity.FlightReservationEntity;
import com.santander.proyectofinal.entity.HotelBookingEntity;
import com.santander.proyectofinal.entity.HotelEntity;
import com.santander.proyectofinal.repository.IFlightEntityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightService {
    @Autowired
    private IFlightEntityRepository flightEntityRepository;
    private ModelMapper modelMapper = new ModelMapper();


    public FlightDTO add(FlightDTO flightDTO) {
        FlightEntity flightEntity = modelMapper.map(flightDTO, FlightEntity.class);
        flightEntityRepository.save(flightEntity);
        return flightDTO;
    }

    public FlightListResponseDTO getFlights() {
        List<FlightEntity> flightEntities = flightEntityRepository.findAll();
        return new FlightListResponseDTO(
                flightEntities.stream().map(
                                flight -> modelMapper.map(flight, FlightDTO.class)
                        )
                        .collect(Collectors.toList())
        );
    }

    public FlightListResponseDTO getFlightsByDateAndOriginAndDestiny(String origin, String destiny, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateFromParse = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToParse = LocalDate.parse(dateTo, formatter);

        List<FlightEntity> flightEntities = flightEntityRepository.findAllByDateFromLessThanEqualAndDateToGreaterThanEqualAndOriginEqualsAndDestinyEquals(dateFromParse, dateToParse, origin, destiny);
        if (flightEntities.isEmpty()) throw new RuntimeException();
        return new FlightListResponseDTO(
                flightEntities.stream().map(
                                flight -> modelMapper.map(flight, FlightDTO.class)
                        )
                        .collect(Collectors.toList())
        );

    }


    public FlightDTO update(Integer id, FlightDTO flightDTO) {
        if (flightEntityRepository.findById(id).isPresent()) {
            FlightEntity flightEntity = modelMapper.map(flightDTO, FlightEntity.class);
            flightEntity.setId(id);
            flightEntityRepository.save(flightEntity);
        } else {
            throw new RuntimeException();
        }
        return flightDTO;
    }

    public FlightDTO deleteFlight(String flightNumber) {
            FlightEntity flightEntity = flightEntityRepository.findByFlightNumberEquals(flightNumber).orElseThrow();
            List<FlightReservationEntity> flightReservationEntityList = flightEntityRepository.findIfExistReservation(flightNumber);
            if (!flightReservationEntityList.isEmpty()) {throw new RuntimeException("no se puede borrar vuelo porque tiene reservas");}
            flightEntityRepository.delete(flightEntity);
            FlightDTO flightDTO = modelMapper.map(flightEntity, FlightDTO.class);
            return flightDTO;

    }
}


