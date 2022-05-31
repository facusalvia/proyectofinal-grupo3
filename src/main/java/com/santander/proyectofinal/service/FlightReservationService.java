package com.santander.proyectofinal.service;

import com.santander.proyectofinal.dto.FlightDTO;
import com.santander.proyectofinal.dto.SuccessDTO;
import com.santander.proyectofinal.dto.request.FlightReservationRequestDTO;
import com.santander.proyectofinal.dto.response.FlightListResponseDTO;
import com.santander.proyectofinal.dto.response.FlightReservationResponseDTO;
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
        flightReservationEntity.setActive(true);
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
          FlightReservationEntity flightReservationEntityRepo =  flightReservationRepository.findById(id).orElseThrow();
            FlightReservationEntity flightReservationEntity = modelMapper.map(flightReservationRequestDTO.getFlightReservationDTO(), FlightReservationEntity.class);
            flightReservationEntity.setId(id);
            flightReservationEntity.getPaymentMethod().setId(flightReservationEntityRepo.getPaymentMethod().getId());
            flightReservationEntity.setFlightEntity(flightReservationEntityRepo.getFlightEntity());
        flightReservationEntity.setUsername(flightReservationRequestDTO.getUsername());
        for (int i = 0; i <flightReservationEntity.getPeople().size() ; i++) {
            flightReservationEntity.getPeople().get(i).setId(flightReservationEntityRepo.getPeople().get(i).getId());
        }
            flightReservationRepository.save(flightReservationEntity);
        return flightReservationRequestDTO;
    }

    public FlightReservationResponseDTO deleteFlightReservation(Integer id) {
        FlightReservationEntity flightReservationEntity = flightReservationRepository.findById(id).orElseThrow();
        flightReservationEntity.setActive(false);
        flightReservationRepository.save(flightReservationEntity);
        FlightReservationResponseDTO flightReservationResponseDTO = modelMapper.map(flightReservationEntity, FlightReservationResponseDTO.class);
        return flightReservationResponseDTO;
    }

}
