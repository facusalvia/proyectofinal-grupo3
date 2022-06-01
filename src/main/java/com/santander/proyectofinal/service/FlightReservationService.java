package com.santander.proyectofinal.service;

import com.santander.proyectofinal.dto.request.FlightReservationRequestDTO;
import com.santander.proyectofinal.dto.response.FlightReservationResponseDTO;
import com.santander.proyectofinal.dto.response.FlightReservationResponseListDTO;
import com.santander.proyectofinal.entity.FlightEntity;
import com.santander.proyectofinal.entity.FlightReservationEntity;
import com.santander.proyectofinal.entity.PersonEntity;
import com.santander.proyectofinal.repository.IFlightEntityRepository;
import com.santander.proyectofinal.repository.IFlightReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

        flightReservationEntity.setCreatedAt(LocalDate.now());
        flightReservationEntity.setTotalAmount(flightReservationEntity.getSeats()* flightReservationEntity.getFlightEntity().getPricePerPerson());
        flightReservationRepository.save(flightReservationEntity);
        if (flightReservationEntity.getId() == null) {
            throw new RuntimeException("Error al reservar el vuelo");
        }

        return flightReservationRequestDTO;
    }


    public FlightReservationRequestDTO update(Integer id, FlightReservationRequestDTO flightReservationRequestDTO) {
        FlightReservationEntity flightReservationEntityRepo = flightReservationRepository.findById(id).orElseThrow();
        FlightReservationEntity flightReservationEntity = buildFlightReservationEntity(id, flightReservationRequestDTO, flightReservationEntityRepo);
        flightReservationRepository.save(flightReservationEntity);
        return flightReservationRequestDTO;
    }


    //TODO: Verificar porque duplica en la tabla intermerdia
    private FlightReservationEntity buildFlightReservationEntity(Integer id, FlightReservationRequestDTO flightReservationRequestDTO, FlightReservationEntity flightReservationEntityRepo) {
        FlightReservationEntity flightReservationEntity = modelMapper.map(flightReservationRequestDTO.getFlightReservationDTO(), FlightReservationEntity.class);
        flightReservationEntity.setId(id);
        flightReservationEntity.getPaymentMethod().setId(flightReservationEntityRepo.getPaymentMethod().getId());
        flightReservationEntity.setFlightEntity(flightReservationEntityRepo.getFlightEntity());
        flightReservationEntity.setUsername(flightReservationRequestDTO.getUsername());
        for (int i = 0; i < flightReservationRequestDTO.getFlightReservationDTO().getPeople().size(); i++) {
            if (flightReservationEntityRepo.getPeople().size() > i) {
                flightReservationEntity.getPeople().get(i).setId(flightReservationEntityRepo.getPeople().get(i).getId());
            } else {
                flightReservationEntity.getPeople().add(flightReservationEntity.getPeople().get(i));
            }
        }
        return flightReservationEntity;
    }

    public FlightReservationResponseDTO deleteFlightReservation(Integer id) {
        FlightReservationEntity flightReservationEntity = flightReservationRepository.findById(id).orElseThrow();
        flightReservationEntity.setActive(false);
        flightReservationRepository.save(flightReservationEntity);
        FlightReservationResponseDTO flightReservationResponseDTO = modelMapper.map(flightReservationEntity, FlightReservationResponseDTO.class);
        return flightReservationResponseDTO;
    }

    public FlightReservationResponseListDTO getReservations() {

        List<FlightReservationEntity> flightReservationEntityList = flightReservationRepository.findAll();

        List<FlightReservationResponseDTO> flightReservationResponseDTOS = flightReservationEntityList.stream().map(
                reservation -> modelMapper.map(reservation, FlightReservationResponseDTO.class)
        ).collect(Collectors.toList());

        return new FlightReservationResponseListDTO(flightReservationResponseDTOS);

    }
}
