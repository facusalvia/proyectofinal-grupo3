package com.santander.proyectofinal.unitTest;

import com.santander.proyectofinal.dto.FlightDTO;
import com.santander.proyectofinal.dto.PaymentMethodDTO;
import com.santander.proyectofinal.dto.response.FlightListResponseDTO;
import com.santander.proyectofinal.entity.FlightEntity;
import com.santander.proyectofinal.entity.FlightReservationEntity;
import com.santander.proyectofinal.exceptions.PaymentMethodDebitCanNotMoreThanOneDueException;
import com.santander.proyectofinal.exceptions.flightException.FlightNoAvailableException;
import com.santander.proyectofinal.repository.IFlightEntityRepository;
import com.santander.proyectofinal.service.FlightService;
import com.santander.proyectofinal.util.FlightEntityFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
        List<FlightDTO>flightDTOList = new ArrayList<>();
        flightDTOList.add(FlightEntityFactory.newFlightDTO());
        expectedFlightListResponseDTO.setFlights(flightDTOList);

        //Act
        when(flightEntityRepository.findAll()).thenReturn(expectedFlights);
        FlightListResponseDTO obtainedFlightListResponseDTO = flightService.getFlights();

        //Assert
        assertEquals(expectedFlightListResponseDTO,obtainedFlightListResponseDTO);
    }
    @Test
    void shouldUpdateAFlight(){
        //Arrange
        FlightEntity flightEntity = FlightEntityFactory.newFlightEntity();
        FlightDTO expectedFlightDTO = FlightEntityFactory.newFlightDTO();
        //Act
        when(flightEntityRepository.findByFlightNumberEquals(any())).thenReturn(Optional.of(flightEntity));
        when(flightEntityRepository.save(any())).thenReturn(flightEntity);

        FlightDTO obtainedFlightDTO = flightService.update(expectedFlightDTO.getFlightNumber(),expectedFlightDTO);

        //Assert
        assertEquals(expectedFlightDTO,obtainedFlightDTO);
    }
    @Test
    void shouldReturnAFlightByDateAndDestination(){
        //Arrange
        FlightEntity flightEntity = FlightEntityFactory.newFlightEntity();
        List<FlightEntity> obtainedFlights = new ArrayList<>();
        obtainedFlights.add(flightEntity);
        FlightListResponseDTO expectedFlights = new FlightListResponseDTO();
        List<FlightDTO>flightDTOList = new ArrayList<>();
        flightDTOList.add(FlightEntityFactory.newFlightDTO());
        expectedFlights.setFlights(flightDTOList);
        LocalDate from = LocalDate.of(2022,06,02);
        LocalDate to = LocalDate.of(2022,06,12);
        String stringFrom = "02/06/2022";
        String stringTo = "12/06/2022";
        String origin = "Florencio Varela";
        String destination = "Solano";

        //Act
        when(flightEntityRepository.findAllByDateFromLessThanEqualAndDateToGreaterThanEqualAndOriginEqualsAndDestinyEquals(from,to,origin,destination)).thenReturn(obtainedFlights);
        FlightListResponseDTO obtainedFlightsDTO = flightService.getFlightsByDateAndOriginAndDestiny(origin,destination,stringFrom,stringTo);

        //Assert
        assertEquals(expectedFlights,obtainedFlightsDTO);
    }

    @Test
    void shouldDeleteAFlight(){
        //Arrange
        FlightEntity flightEntity = FlightEntityFactory.newFlightEntity();
        FlightDTO expectedFlight = FlightEntityFactory.newFlightDTO();
        List<FlightReservationEntity> flightReservationEntityList = new ArrayList<>();
        //Act
        when(flightEntityRepository.findByFlightNumberEquals(any())).thenReturn(Optional.of(flightEntity));
        when(flightEntityRepository.findIfExistReservation(any())).thenReturn(flightReservationEntityList);

        FlightDTO obtainedFlight = flightService.deleteFlight(flightEntity.getFlightNumber());

        //Assert
        assertEquals(expectedFlight,obtainedFlight);
    }

    @Test
    void shouldReturnFlightsSortedByCostInAnyDates(){
        List<FlightDTO> expectedFlightsList = new ArrayList<>();
        expectedFlightsList.add(FlightEntityFactory.newFlightDTO());
        expectedFlightsList.add(FlightEntityFactory.newFlightDTO());
        FlightListResponseDTO expectedList = new FlightListResponseDTO(expectedFlightsList);

        List<FlightEntity> toAddFlightsEntityList = new ArrayList<>();
        toAddFlightsEntityList.add(FlightEntityFactory.newFlightEntity());
        toAddFlightsEntityList.add(FlightEntityFactory.newFlightEntity());

        when(flightEntityRepository.findByDateFromAndDateToSortedByCost(any(),any())).thenReturn(toAddFlightsEntityList);

        LocalDate from = LocalDate.of(2022,06,02);
        LocalDate to = LocalDate.of(2022,06,10);

        FlightListResponseDTO obtainedList = flightService.getFlightsSortedByCost(from,to);


        assertEquals(expectedList,obtainedList);
    }

    @Test
    void shouldThrowFlightNoAvailableExceptionWhenSearchFlightsSortedByCostInAnyDates(){
        LocalDate from = LocalDate.of(2024,06,02);
        LocalDate to = LocalDate.of(2024,06,10);
        List<FlightEntity> flightEntities = new ArrayList<>();

        when(flightEntityRepository.findByDateFromAndDateToSortedByCost(any(),any())).thenReturn(flightEntities);
        assertThrows(FlightNoAvailableException.class,()-> flightService.getFlightsSortedByCost(from,to));
    }
}
