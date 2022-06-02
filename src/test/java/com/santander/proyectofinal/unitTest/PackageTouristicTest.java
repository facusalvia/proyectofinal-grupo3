package com.santander.proyectofinal.unitTest;
import com.santander.proyectofinal.dto.request.TouristicPackageRequestDTO;
import com.santander.proyectofinal.dto.response.ListTouristicPackageResponseDTO;
import com.santander.proyectofinal.dto.response.TouristicPackageResponseDTO;
import com.santander.proyectofinal.entity.TouristicPackageDiscountTypeEntity;
import com.santander.proyectofinal.entity.TouristicPackageEntity;
import com.santander.proyectofinal.entity.UserEntity;
import com.santander.proyectofinal.repository.*;
import com.santander.proyectofinal.service.TouristicPackageService;
import com.santander.proyectofinal.util.FlightReservationFactory;
import com.santander.proyectofinal.util.HotelBookingEntityFactory;
import com.santander.proyectofinal.util.TouristicPackageFactory;
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
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class PackageTouristicTest {
    @Mock
    ITouristicPackageRepository touristicPackageRepository;
    @Mock
    ITouristicPackageDiscountTypeRepository touristicPackageDiscountTypeRepository;
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
        when(touristicPackageDiscountTypeRepository.findById(1)).thenReturn(Optional.of(new TouristicPackageDiscountTypeEntity(1,0.1,null)));
        when(flightReservationRepository.findById(any())).thenReturn(Optional.of(FlightReservationFactory.newFlightReservationEntity()));
        when(userEntityRepository.findById(any())).thenReturn(Optional.of(new UserEntity(1,"test user","1234","admin")));
        when(touristicPackageRepository.save(any())).thenReturn(TouristicPackageFactory.newTouristicPackageEntity());
        TouristicPackageRequestDTO obtainedTouristicPackage = touristicPackageService.addTouristicPackage(touristicPackageRequestDTO);

        //Assert
        assertAll(()-> assertEquals(obtainedTouristicPackage,touristicPackageRequestDTO));

    }
/*
    @Test
    void shouldReturnAllPackageTouristics(){
        //Arrange
        List<TouristicPackageEntity> touristicPackageEntityList = new ArrayList<>();
        touristicPackageEntityList.add(1,TouristicPackageFactory.newTouristicPackageEntity());
        ListTouristicPackageResponseDTO listTouristicPackageResponseDTOExpected = new ListTouristicPackageResponseDTO();
        List<TouristicPackageResponseDTO> touristicPackageResponseDTOList = new ArrayList<>();
        touristicPackageResponseDTOList.add(TouristicPackageFactory.newTouristicPackageResponseDTO());
        listTouristicPackageResponseDTOExpected.setTouristicPackages(touristicPackageResponseDTOList);
        //Act
        when(touristicPackageRepository.findAll()).thenReturn(touristicPackageEntityList);
        ListTouristicPackageResponseDTO listTouristicPackageResponseDTO =  touristicPackageService.getTouristicPackages();

        //Assert
        assertAll(()-> assertEquals(listTouristicPackageResponseDTOExpected,listTouristicPackageResponseDTO));


    }*/



}
