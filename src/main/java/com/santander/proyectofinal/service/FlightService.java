package com.santander.proyectofinal.service;

import com.santander.proyectofinal.dto.FlightDTO;

import com.santander.proyectofinal.dto.TaskMessage;
import com.santander.proyectofinal.entity.FlightEntity;
import com.santander.proyectofinal.repository.IFlightEntityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
