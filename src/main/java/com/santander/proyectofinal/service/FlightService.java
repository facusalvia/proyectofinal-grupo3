package com.santander.proyectofinal.service;

import com.santander.proyectofinal.dto.FlightDTO;
import com.santander.proyectofinal.dto.response.FlightListResponseDTO;
import com.santander.proyectofinal.entity.FlightEntity;
import com.santander.proyectofinal.entity.FlightReservationEntity;
import com.santander.proyectofinal.entity.HotelEntity;
import com.santander.proyectofinal.exceptions.flightException.FlightAlreadyExistsException;
import com.santander.proyectofinal.exceptions.flightException.FlightCanNotDeleteException;
import com.santander.proyectofinal.exceptions.flightException.FlightDoesNotExistException;
import com.santander.proyectofinal.exceptions.flightException.FlightNoAvailableException;
import com.santander.proyectofinal.exceptions.hotelException.HotelAlreadyExistsException;
import com.santander.proyectofinal.exceptions.RepositorySaveException;
import com.santander.proyectofinal.exceptions.hotelException.HotelDoesNotExistException;
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
        if(flightEntityRepository.findByFlightNumberEquals(flightDTO.getFlightNumber()).isPresent()){
            throw new FlightAlreadyExistsException();
        }

        flightEntity=flightEntityRepository.save(flightEntity);
        if(flightEntity.getId() == null){
            throw new RepositorySaveException();
        }
        return flightDTO;
    }

    public FlightListResponseDTO getFlights() {
        List<FlightEntity> flightEntities = flightEntityRepository.findAll();
        if (flightEntities.isEmpty())
            throw new FlightNoAvailableException();
        return new FlightListResponseDTO(
                flightEntities.stream().map(
                                flight -> modelMapper.map(flight, FlightDTO.class)
                        )
                        .collect(Collectors.toList())
        );
    }

    public FlightListResponseDTO getFlightsByDateAndOriginAndDestiny(String origin, String destiny, LocalDate dateFrom, LocalDate dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        List<FlightEntity> flightEntities = flightEntityRepository.findAllByDateFromLessThanEqualAndDateToGreaterThanEqualAndOriginEqualsAndDestinyEquals(dateFrom, dateTo, origin, destiny);
        if (flightEntities.isEmpty()) throw new FlightNoAvailableException();
        return new FlightListResponseDTO(
                flightEntities.stream().map(
                                flight -> modelMapper.map(flight, FlightDTO.class)
                        )
                        .collect(Collectors.toList())
        );

    }


    public FlightDTO update(String flightNumber, FlightDTO flightDTO) {
        FlightEntity flightEntity = flightEntityRepository.findByFlightNumberEquals(flightNumber).orElseThrow(FlightDoesNotExistException::new);
        Integer idFlight = flightEntity.getId();
        flightEntity = modelMapper.map(flightDTO, FlightEntity.class);
        flightEntity.setId(idFlight);
        flightEntityRepository.save(flightEntity);
        return flightDTO;

    }

    public FlightDTO deleteFlight(String flightNumber) {
            FlightEntity flightEntity = flightEntityRepository.findByFlightNumberEquals(flightNumber).orElseThrow(FlightDoesNotExistException::new);
            List<FlightReservationEntity> flightReservationEntityList = flightEntityRepository.findIfExistReservation(flightNumber);
            if (!flightReservationEntityList.isEmpty()) {throw new FlightCanNotDeleteException();}
            flightEntityRepository.delete(flightEntity);
            FlightDTO flightDTO = modelMapper.map(flightEntity, FlightDTO.class);
            return flightDTO;

    }
}


