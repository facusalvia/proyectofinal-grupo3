package com.santander.proyectofinal.service;

import com.santander.proyectofinal.dto.FlightDTO;
import com.santander.proyectofinal.dto.SuccessDTO;
import com.santander.proyectofinal.dto.request.FlightReservationRequestDTO;
import com.santander.proyectofinal.entity.FlightEntity;
import com.santander.proyectofinal.entity.FlightReservationEntity;
import com.santander.proyectofinal.entity.PersonEntity;
import com.santander.proyectofinal.repository.IFlightEntityRepository;
import com.santander.proyectofinal.repository.IFlightReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightReservationService {
    @Autowired
    IFlightReservationRepository flightReservationRepository;
    @Autowired
    IFlightEntityRepository flightEntityRepository;

    ModelMapper modelMapper = new ModelMapper();

    public FlightReservationRequestDTO reservation(FlightReservationRequestDTO flightReservationRequestDTO) {
        FlightEntity flightEntity = flightEntityRepository.findByFlightNumberEquals(flightReservationRequestDTO.getFlightReservationDTO().getFlightNumber()).orElseThrow();
        FlightReservationEntity flightReservationEntity = modelMapper.map(flightReservationRequestDTO.getFlightReservationDTO(), FlightReservationEntity.class);
        flightReservationEntity.setFlightEntity(flightEntity);
        flightReservationEntity.setUsername(flightReservationRequestDTO.getUsername());
        List<PersonEntity> passengers = flightReservationEntity.getPeople().stream().map(
                        person -> modelMapper.map(person, PersonEntity.class)
                )
                .collect(Collectors.toList());
        for (PersonEntity person : passengers) {
            person.setFlightReservationEntities(List.of(flightReservationEntity));
        }
        flightReservationRepository.save(flightReservationEntity);
        if (flightReservationEntity.getId() == null) {
            throw new RuntimeException("Error al reservar el vuelo");
        }

        return flightReservationRequestDTO;
    }


    public FlightReservationRequestDTO update(Integer id, FlightReservationRequestDTO flightReservationRequestDTO) {
        FlightReservationEntity flightReservationEntity = flightReservationRepository.findById(id).orElseThrow();
        FlightReservationEntity newFlightReservationEntity = modelMapper.map(flightReservationRequestDTO, FlightReservationEntity.class);
        flightReservationEntity.setPeople(newFlightReservationEntity.getPeople());
        flightReservationEntity.setUsername(flightReservationRequestDTO.getUsername());
       //List<PersonEntity> passengers = flightReservationEntity.getPeople().stream().map(
       //                person -> modelMapper.map(person, PersonEntity.class)
       //        )
       //        .collect(Collectors.toList());
       // for (PersonEntity person : passengers) {
       //     person.setFlightReservationEntities(List.of(flightReservationEntity));
       // }
      //  flightReservationEntity.setId(id);
       flightReservationRepository.save(flightReservationEntity);

        return flightReservationRequestDTO;
    }
}
