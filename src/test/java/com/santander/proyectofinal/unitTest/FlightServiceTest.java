package com.santander.proyectofinal.unitTest;

import com.santander.proyectofinal.dto.FlightDTO;
import com.santander.proyectofinal.dto.response.FlightListResponseDTO;
import com.santander.proyectofinal.entity.FlightEntity;
import com.santander.proyectofinal.repository.IFlightEntityRepository;
import com.santander.proyectofinal.service.FlightService;
import com.santander.proyectofinal.util.FlightEntityFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FlightServiceTest {
    @Mock
    IFlightEntityRepository flightEntityRepository;

    @InjectMocks
    FlightService flightService;

    @Test
    void shouldReturnAnAddedFlight(){
        //Arrange
        FlightDTO flightToBeAdded = FlightEntityFactory.newFlightDTO();
        FlightEntity mockedFlight = FlightEntityFactory.newFlightEntity();
        //Act
        when(flightEntityRepository.findByFlightNumberEquals(flightToBeAdded.getFlightNumber())).thenReturn(Optional.empty());
        when(flightEntityRepository.save(any())).thenReturn(mockedFlight);
        FlightDTO addedFlight = flightService.add(flightToBeAdded);
        //Assert
        assertAll(()->assertEquals(flightToBeAdded,addedFlight));
    }
    @Test
    void shouldReturnAllFlights(){
        //Arrange
        FlightEntity flightEntity = FlightEntityFactory.newFlightEntity();
        List<FlightEntity> expectedFlights = new ArrayList<>();
        expectedFlights.add(flightEntity);

        FlightListResponseDTO expectedFlightListResponseDTO = new FlightListResponseDTO();
    }
}
