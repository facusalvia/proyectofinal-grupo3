package com.santander.proyectofinal.unitTest;
import com.santander.proyectofinal.dto.request.HotelRequestDTO;
import com.santander.proyectofinal.entity.HotelEntity;
import com.santander.proyectofinal.repository.ITouristicPackageRepository;
import com.santander.proyectofinal.service.TouristicPackageService;
import com.santander.proyectofinal.util.HotelEntityFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PackageTouristicTest {
    @Mock
    ITouristicPackageRepository touristicPackageRepository;

    @InjectMocks
    TouristicPackageService touristicPackageService;

    @Test
    void shouldReturnAnAddedHotel(){

        HotelEntity hotelEntity = HotelEntityFactory.newHotelEntity();

        HotelRequestDTO hotelToAdd= HotelEntityFactory.newHotelRequestDTO();
        HotelEntity mockedHotel = HotelEntityFactory.newHotelEntity();

        when(hotelRepository.findByHotelCode(hotelToAdd.getHotelCode())).thenReturn(Optional.empty());
        when(hotelRepository.save(any())).thenReturn(mockedHotel);

        HotelRequestDTO addedHotel = hotelService.addHotel(hotelToAdd);
        assertAll(()-> assertEquals(hotelToAdd,addedHotel));

    }

}
