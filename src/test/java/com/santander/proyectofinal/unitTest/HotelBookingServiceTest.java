package com.santander.proyectofinal.unitTest;


import com.santander.proyectofinal.entity.HotelEntity;
import com.santander.proyectofinal.repository.IHotelBookingRepository;
import com.santander.proyectofinal.repository.IHotelRepository;
import com.santander.proyectofinal.repository.ITouristicPackageRepository;
import com.santander.proyectofinal.service.HotelBookingService;
import com.santander.proyectofinal.service.InterestService;
import com.santander.proyectofinal.util.HotelEntityFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        //HotelEntity hotelEntity = HotelEntityFactory.newHotelEntity();
    }
}
