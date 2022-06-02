package com.santander.proyectofinal.unitTest;


import com.santander.proyectofinal.dto.request.HotelBookingDTORequest;
import com.santander.proyectofinal.dto.response.ListHotelBookingResponseDTO;
import com.santander.proyectofinal.entity.HotelBookingEntity;
import com.santander.proyectofinal.entity.HotelEntity;
import com.santander.proyectofinal.entity.TouristicPackageEntity;
import com.santander.proyectofinal.repository.IHotelBookingRepository;
import com.santander.proyectofinal.repository.IHotelRepository;
import com.santander.proyectofinal.repository.ITouristicPackageRepository;
import com.santander.proyectofinal.service.HotelBookingService;
import com.santander.proyectofinal.service.InterestService;
import com.santander.proyectofinal.util.HotelBookingEntityFactory;
import com.santander.proyectofinal.util.HotelEntityFactory;
import com.santander.proyectofinal.util.TouristicPackageFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HotelBookingServiceTest{

    @Mock
    IHotelRepository hotelRepository;

    @Mock
    IHotelBookingRepository hotelBookingRepository;

    @Mock
    InterestService interestService;

    @Mock
    ITouristicPackageRepository touristicPackageRepository;

    @InjectMocks
    HotelBookingService hotelBookingService;

    @Test
    void shouldReturnAnAddedBookingHotel(){
        HotelEntity expectedHotelEntity = HotelEntityFactory.newHotelEntity();
        Double expectedInterest = 1.1;
        HotelBookingEntity expectedHotelBookingEntity = HotelBookingEntityFactory.newHotelBookingEntity();
        HotelBookingDTORequest expectedHotelBookingDTORequest = HotelBookingEntityFactory.newHotelBookingDTORequest();
        HotelBookingDTORequest toAddHotelBookingDTORequest = HotelBookingEntityFactory.newHotelBookingDTORequest();

        when(hotelRepository.findByHotelCode(any())).thenReturn(Optional.of(expectedHotelEntity));
        when(interestService.interestCalculator(any())).thenReturn(expectedInterest);
        when(hotelBookingRepository.save(any())).thenReturn(expectedHotelBookingEntity);

        HotelBookingDTORequest obtainedHotelBookingDTORequest = hotelBookingService.addBooking(toAddHotelBookingDTORequest);
        assertEquals(expectedHotelBookingDTORequest,obtainedHotelBookingDTORequest);
    }

    @Test
    void shouldReturnAllHotelBookings(){
        List<HotelBookingEntity> expectedListHotelBookings = new ArrayList<>();
        expectedListHotelBookings.add(HotelBookingEntityFactory.newHotelBookingEntity());
        ListHotelBookingResponseDTO expectedListHotelBookingResponseDTO = new ListHotelBookingResponseDTO();
        List<HotelBookingDTORequest> listBookings = new ArrayList<>();
        listBookings.add(HotelBookingEntityFactory.newHotelBookingDTORequest());

        expectedListHotelBookingResponseDTO.setHotel_bookings(listBookings);

        when(hotelBookingRepository.findByIsActiveTrue()).thenReturn(expectedListHotelBookings);

        ListHotelBookingResponseDTO obtainedListHotelBookingResponseDTO = hotelBookingService.getHotelBookings();

        assertEquals(expectedListHotelBookingResponseDTO,obtainedListHotelBookingResponseDTO);

    }

    @Test
    void shouldReturnAHotelBookingDeleted(){
        HotelBookingEntity expectedHotelBookingEntity = HotelBookingEntityFactory.newHotelBookingEntity();
        HotelBookingDTORequest expectedHotelBookingDTORequest = HotelBookingEntityFactory.newHotelBookingDTORequest();
        List<TouristicPackageEntity> expectedTouristicPackageEntityList = new ArrayList<>();

        when(hotelBookingRepository.findById(any())).thenReturn(Optional.of(expectedHotelBookingEntity));
        when(touristicPackageRepository.findPackagesByHotelBooking(any())).thenReturn(expectedTouristicPackageEntityList);
        when(hotelBookingRepository.save(any())).thenReturn(expectedHotelBookingEntity);

        HotelBookingDTORequest obtainedHotelBookingDTORequest = hotelBookingService.deleteHotelBooking(expectedHotelBookingEntity.getId());

        assertEquals(expectedHotelBookingDTORequest,obtainedHotelBookingDTORequest);
    }

    @Test
    void shouldReturnAHotelBookingUpdated(){
        HotelBookingEntity expectedHotelBookingEntity = HotelBookingEntityFactory.newHotelBookingEntity();
        HotelBookingDTORequest expectedHotelBookingDTORequest = HotelBookingEntityFactory.newHotelBookingDTORequest();

        when(hotelBookingRepository.findById(any())).thenReturn(Optional.of(expectedHotelBookingEntity));
        when(hotelBookingRepository.save(any())).thenReturn(expectedHotelBookingEntity);

        HotelBookingDTORequest obtainedHotelBookingDTORequest = hotelBookingService.updateHotelBooking(expectedHotelBookingEntity.getId(),expectedHotelBookingDTORequest);

        assertEquals(expectedHotelBookingDTORequest,obtainedHotelBookingDTORequest);

    }
}
