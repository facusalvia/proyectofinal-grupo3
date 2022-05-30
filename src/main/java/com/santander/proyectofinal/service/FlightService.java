package com.santander.proyectofinal.service;

import com.santander.proyectofinal.dto.FlightDTO;

import com.santander.proyectofinal.dto.TaskMessage;
import com.santander.proyectofinal.dto.response.FlightListResponseDTO;
import com.santander.proyectofinal.entity.FlightEntity;
import com.santander.proyectofinal.repository.IFlightEntityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public List<FlightDTO> getFlightsByDate(String origen, String destino, LocalDate fechaDesde, LocalDate fechaHasta) {
        List<FlightEntity> vuelos = flightEntityRepository.getFlights();
        List<FlightEntity> vuelosEncontrados = vueloDao.obtenerVuelosDisponiblesOptimizado(origen, destino, fechaDesde, fechaHasta);
        List<FlightDTO> vuelosDtos = vuelosEncontrados.stream().map(
                vuelo -> new FlightDTO(vuelo.getNroVuelo(), vuelo.getFechaIda(),
                        vuelo.getFechaVuelta(), vuelo.getPrecioPersona(),
                        vuelo.getOrigen(), vuelo.getDestino(), vuelo.getTipoAsiento())
        ).collect(Collectors.toList());
        if (vuelosDtos.isEmpty()) throw new RuntimeException();
        return vuelosDtos;
    }


    public FlightDTO update(FlightDTO flightDTO) {
      FlightEntity flightEntity = flightEntityRepository
              .findByFlightNumberEquals(flightDTO.getFlightNumber())
              .orElseThrow(()->{throw new RuntimeException();});
        flightEntity = modelMapper.map(flightDTO,FlightEntity.class);
        flightEntityRepository.update(flightEntity).orElseThrow(()->{throw new RuntimeException();});
        return flightDTO;
    }
}
