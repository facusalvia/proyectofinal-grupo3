package com.santander.proyectofinal.unitTest;

import com.santander.proyectofinal.dto.request.FlightReservationRequestDTO;
import com.santander.proyectofinal.dto.response.FlightReservationResponseDTO;
import com.santander.proyectofinal.dto.response.FlightReservationResponseListDTO;
import com.santander.proyectofinal.entity.ClientEntity;
import com.santander.proyectofinal.entity.FlightEntity;
import com.santander.proyectofinal.entity.FlightReservationEntity;
import com.santander.proyectofinal.entity.TouristicPackageEntity;
import com.santander.proyectofinal.repository.IClientRepository;
import com.santander.proyectofinal.repository.IFlightEntityRepository;
import com.santander.proyectofinal.repository.IFlightReservationRepository;
import com.santander.proyectofinal.repository.ITouristicPackageRepository;
import com.santander.proyectofinal.service.FlightReservationService;
import com.santander.proyectofinal.service.InterestService;
import com.santander.proyectofinal.util.FlightEntityFactory;
import com.santander.proyectofinal.util.FlightReservationFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FlightReservationServiceTest {

    @Mock
    IFlightEntityRepository flightEntityRepository;

    @Mock
    IFlightReservationRepository flightReservationRepository;

    @Mock
    InterestService interestService;

    @Mock
    ITouristicPackageRepository touristicPackageRepository;

    @InjectMocks
    FlightReservationService flightReservationService;

    @Mock
    IClientRepository clientRepository;

    @Test
    void shouldAddAFlightReservation() {
        //Arrange
        FlightEntity flightEntityMocked = FlightEntityFactory.newFlightEntity();
        FlightReservationRequestDTO expectedFlightReservationRequestDTO = FlightReservationFactory.newFlightReservationRequestDTO();
        FlightReservationRequestDTO toAddFlightReservationRequestDTO = FlightReservationFactory.newFlightReservationRequestDTO();
        FlightReservationEntity flightReservationEntity = FlightReservationFactory.newFlightReservationEntity();
        //Act
        when(flightEntityRepository.findByFlightNumberEquals(any())).thenReturn(Optional.of(flightEntityMocked));
        when(interestService.interestCalculator(any())).thenReturn(1.1);
        when(flightReservationRepository.save(any())).thenReturn(flightReservationEntity);
        when(clientRepository.findByUsernameEquals(any())).thenReturn(Optional.of(new ClientEntity(1, "", "", "", null, null)));
        FlightReservationRequestDTO obtainedFlightReservationRequestDTO = flightReservationService.reservation(toAddFlightReservationRequestDTO);

        //Assert
        assertEquals(expectedFlightReservationRequestDTO,obtainedFlightReservationRequestDTO);

    }
    @Test
    void shouldReturnAFlightReservationUpdated(){
        FlightReservationEntity expectedFlightReservationEntity = FlightReservationFactory.newFlightReservationEntity();
        FlightReservationRequestDTO expectedFlightReservationRequestDTO =FlightReservationFactory.newFlightReservationRequestDTO();

        when(flightReservationRepository.findById(any())).thenReturn(Optional.of(expectedFlightReservationEntity));
        when(flightReservationRepository.save(any())).thenReturn(expectedFlightReservationEntity);

        FlightReservationRequestDTO flightReservationRequestDTO = flightReservationService.update(expectedFlightReservationEntity.getId(),expectedFlightReservationRequestDTO);

        assertEquals(expectedFlightReservationRequestDTO,flightReservationRequestDTO);

    }
    @Test
    void shouldReturnAllFlightsReservation(){
        List<FlightReservationEntity> expectedListFlightReservationEntity = new ArrayList<>();
        expectedListFlightReservationEntity.add(FlightReservationFactory.newFlightReservationEntity());
        FlightReservationResponseListDTO expectedFlightListResponseDTO = new FlightReservationResponseListDTO();
        List<FlightReservationResponseDTO> flightReservationResponseDTOList = new ArrayList<>();
        flightReservationResponseDTOList.add(FlightReservationFactory.newFlightReservationResponseDTO());
        expectedFlightListResponseDTO.setFlightReservationResponseDTOList(flightReservationResponseDTOList);

        when(flightReservationRepository.findAll()).thenReturn(expectedListFlightReservationEntity);

        FlightReservationResponseListDTO obtainedFlightListResponseDTO = flightReservationService.getReservations();

        assertEquals(expectedFlightListResponseDTO,obtainedFlightListResponseDTO);
    }
    @Test
    void shouldDeleteAFlightReservation(){
        //Arrange
        FlightReservationResponseDTO expectedFlightReservationRequestDTO = FlightReservationFactory.newFlightReservationResponseDTO();
        FlightReservationEntity flightReservationEntity = FlightReservationFactory.newFlightReservationEntity();
        List<TouristicPackageEntity> touristicPackageEntitiesVoid = new ArrayList<>();
        //Act
        when(flightReservationRepository.findById(any())).thenReturn(Optional.of(flightReservationEntity));
        when(touristicPackageRepository.findPackagesByFlightReservation(flightReservationEntity.getId())).thenReturn(touristicPackageEntitiesVoid);
        when(flightReservationRepository.save(any())).thenReturn(flightReservationEntity);

        FlightReservationResponseDTO obtainedFlightReservationRequestDTO = flightReservationService.deleteFlightReservation(flightReservationEntity.getId());

        //Assert
        assertEquals(expectedFlightReservationRequestDTO,obtainedFlightReservationRequestDTO);

    }


}
