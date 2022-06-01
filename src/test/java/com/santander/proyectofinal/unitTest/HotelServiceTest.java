/*package com.santander.proyectofinal.unitTest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.santander.proyectofinal.dto.request.HotelRequestDTO;
import com.santander.proyectofinal.dto.response.HotelResponseDTO;
import com.santander.proyectofinal.entity.HotelBookingEntity;
import com.santander.proyectofinal.entity.HotelEntity;
import com.santander.proyectofinal.repository.IHotelRepository;
import com.santander.proyectofinal.service.HotelService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class HotelServiceTest {

    @Mock
    IHotelRepository hotelRepository;

    @InjectMocks
    HotelService hotelService;

    @Test
    void shouldReturnAnAddedHotel(){


        List<HotelBookingEntity> listBookings = new ArrayList<>();
        HotelEntity hotelEntity = new HotelEntity(1,"NUEVO-001","Hotel Nuevo",
                "La Plata","Double",6200.0,LocalDate.of(2022,06,05),
                LocalDate.of(2022,06,10),false,listBookings);



        HotelRequestDTO hotelToAdd= new HotelRequestDTO("NUEVO-001","Hotel Nuevo",
                "La Plata","Double",6200.0,LocalDate.of(2022,06,05),
                LocalDate.of(2022,06,10),false);


        when(hotelRepository.findByHotelCode(hotelToAdd.getHotelCode())).thenReturn(null);
        //when(hotelRepository.findByHotelCode(hotelToAdd.getHotelCode()).isPresent()).thenReturn(false);
        when(hotelRepository.save(hotelEntity)).thenReturn(hotelEntity);

        HotelRequestDTO addedHotel = hotelService.addHotel(hotelToAdd);

        assertAll(()-> assertEquals(hotelToAdd,addedHotel));



    }

}*/

