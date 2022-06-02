package com.santander.proyectofinal.unitTest;
import com.santander.proyectofinal.dto.request.TouristicPackageRequestDTO;
import com.santander.proyectofinal.entity.UserEntity;
import com.santander.proyectofinal.repository.IFlightReservationRepository;
import com.santander.proyectofinal.repository.IHotelBookingRepository;
import com.santander.proyectofinal.repository.ITouristicPackageRepository;
import com.santander.proyectofinal.repository.IUserEntityRepository;
import com.santander.proyectofinal.service.TouristicPackageService;
import com.santander.proyectofinal.util.FlightReservationFactory;
import com.santander.proyectofinal.util.HotelBookingEntityFactory;
import com.santander.proyectofinal.util.TouristicPackageFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class PackageTouristicTest {
    @Mock
    ITouristicPackageRepository touristicPackageRepository;

    @Mock
    IHotelBookingRepository hotelBookingRepository;

    @Mock
    IFlightReservationRepository flightReservationRepository;

    @Mock
    IUserEntityRepository userEntityRepository;
    @InjectMocks
    TouristicPackageService touristicPackageService;


    @Test
    void shouldReturnAnAddePackageTouristic(){

        //Arrange
        TouristicPackageRequestDTO touristicPackageRequestDTO = TouristicPackageFactory.newTouristicPackageRequestDTO();

        //Act
        when(hotelBookingRepository.findById(any())).thenReturn(Optional.of(HotelBookingEntityFactory.newHotelBookingEntity()));
        when(flightReservationRepository.findById(any())).thenReturn(Optional.of(FlightReservationFactory.newFlightReservationEntity()));
        when(userEntityRepository.findById(any())).thenReturn(Optional.of(new UserEntity(1,"test user","1234","admin")));
        when(touristicPackageRepository.save(any())).thenReturn(TouristicPackageFactory.newTouristicPackageEntity());
        TouristicPackageRequestDTO obtainedTouristicPackage = touristicPackageService.addTouristicPackage(touristicPackageRequestDTO);

        //Assert
        assertAll(()-> assertEquals(obtainedTouristicPackage,touristicPackageRequestDTO));

    }

}
