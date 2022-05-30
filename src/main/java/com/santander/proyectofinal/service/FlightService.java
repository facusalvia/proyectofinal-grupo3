package com.santander.proyectofinal.service;

import com.santander.proyectofinal.dto.FlightDTO;

import com.santander.proyectofinal.dto.TaskMessage;
import com.santander.proyectofinal.dto.response.FlightListResponseDTO;
import com.santander.proyectofinal.entity.FlightEntity;
import com.santander.proyectofinal.repository.IFlightEntityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.lang.model.util.Elements;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightService {
    @Autowired
    private IFlightEntityRepository flightEntityRepository;
    private ModelMapper modelMapper = new ModelMapper();


    public FlightDTO add(FlightDTO flightDTO) {
        FlightEntity flightEntity = modelMapper.map(flightDTO,FlightEntity.class);
        flightEntityRepository.save(flightEntity);
                //.orElseThrow(()->{throw new RuntimeException();});
        return flightDTO;
    }

    public List<FlightDTO> getFlight() {
        List<FlightEntity> flightEntities = flightEntityRepository.getFlights();

        return new FlightListResponseDTO(flightEntities.stream().map(
                flightEntity ->
        ));
    }

    public List<FlightDTO> getFlightsByDate(String origin, String destiny, LocalDate dateFrom, LocalDate dateTo) {
        List<FlightEntity> flights = flightEntityRepository.getFlights();
        List<FlightEntity> filteredFlights = flights.stream().filter(vuelo -> vuelo.getOrigin().equalsIgnoreCase(origin) &&
                vuelo.getDestiny().equalsIgnoreCase(destiny) &&
                vuelo.getDateFrom().isAfter(dateFrom.minusDays(1)) &&
                vuelo.getDateTo().isBefore(dateTo.plusDays(1))).collect(Collectors.toList());

        List<FlightDTO> flightsDTOs = filteredFlights.stream().map(
                vuelo -> new FlightDTO(vuelo.getFlightNumber(), vuelo.getDateFrom(),
                        vuelo.getDateTo(), vuelo.getPricePerPerson(),
                        vuelo.getOrigin(), vuelo.getDestiny(), vuelo.getSeatType())
        ).collect(Collectors.toList());
        if (flightsDTOs.isEmpty()) throw new RuntimeException();
        return flightsDTOs;
    }



    public FlightDTO update(FlightDTO flightDTO) {
      FlightEntity flightEntity = flightEntityRepository
              .findByFlightNumberEquals(flightDTO.getFlightNumber())
              .orElseThrow(()->{throw new RuntimeException();});

        flightEntity = modelMapper.map(flightDTO,FlightEntity.class);


    }
}
