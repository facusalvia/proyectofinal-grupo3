package com.santander.proyectofinal.unitTest;

import com.santander.proyectofinal.dto.request.HotelRequestDTO;
import com.santander.proyectofinal.dto.response.HotelResponseDTO;
import com.santander.proyectofinal.dto.response.ListHotelResponseDto;
import com.santander.proyectofinal.entity.HotelBookingEntity;
import com.santander.proyectofinal.entity.HotelEntity;
import com.santander.proyectofinal.repository.IHotelRepository;
import com.santander.proyectofinal.service.HotelService;
import com.santander.proyectofinal.util.HotelEntityFactory;
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
public class HotelServiceTest {

    @Mock
    IHotelRepository hotelRepository;

    @InjectMocks
    HotelService hotelService;


    @Test
    void shouldReturnAnAddedHotel(){

        //HotelEntity hotelEntity = HotelEntityFactory.newHotelEntity();

        HotelRequestDTO hotelToAdd= HotelEntityFactory.newHotelRequestDTO();
        HotelEntity mockedHotel = HotelEntityFactory.newHotelEntity();

        when(hotelRepository.findByHotelCode(hotelToAdd.getHotelCode())).thenReturn(Optional.empty());
        when(hotelRepository.save(any())).thenReturn(mockedHotel);

        HotelRequestDTO addedHotel = hotelService.addHotel(hotelToAdd);
        assertAll(()-> assertEquals(hotelToAdd,addedHotel));

    }
    @Test
    void shouldReturnAllHotels(){
        //Arrange
        HotelEntity hotelEntity = HotelEntityFactory.newHotelEntity();
        List<HotelEntity> expectedHotels = new ArrayList<>();
        expectedHotels.add(hotelEntity);

        ListHotelResponseDto expectedListHotelResponseDto = new ListHotelResponseDto();
        List<HotelResponseDTO>hotelResponseDTOList= new ArrayList<>();
        hotelResponseDTOList.add(HotelEntityFactory.newHotelResponseDTO());
        expectedListHotelResponseDto.setHotels(hotelResponseDTOList);

        //Act
        when(hotelRepository.findAll()).thenReturn(expectedHotels);
        ListHotelResponseDto obtainedListHotelResponseDto = hotelService.getHotels();

        //Assert
        assertEquals(expectedListHotelResponseDto,obtainedListHotelResponseDto);
    }
    @Test
    void shouldUpdateAnHotel(){
        //Arrange
        HotelEntity hotelEntity = HotelEntityFactory.newHotelEntity();
        HotelRequestDTO expectedHotelRequestDTO = HotelEntityFactory.newHotelRequestDTO();
        //Act
        when(hotelRepository.findByHotelCode(any())).thenReturn(Optional.of(hotelEntity));
        when(hotelRepository.save(any())).thenReturn(hotelEntity);

        HotelRequestDTO obtainHotelRequestDTO = hotelService.updateHotel(expectedHotelRequestDTO.getHotelCode(),expectedHotelRequestDTO);

        //Assert
        assertEquals(expectedHotelRequestDTO,obtainHotelRequestDTO);
    }
    @Test
    void shouldReturnAnHotelByDateAndDestination(){
        //Arrange
        HotelEntity hotelEntity = HotelEntityFactory.newHotelEntity();
        List<HotelEntity> obtainedHotelsEntity = new ArrayList<>();
        obtainedHotelsEntity.add(hotelEntity);
        ListHotelResponseDto expectedListHotelResponseDto = new ListHotelResponseDto();
        List<HotelResponseDTO>hotelResponseDTOList= new ArrayList<>();
        hotelResponseDTOList.add(HotelEntityFactory.newHotelResponseDTO());
        expectedListHotelResponseDto.setHotels(hotelResponseDTOList);
        LocalDate from = LocalDate.of(2022,06,05);
        LocalDate to = LocalDate.of(2022,06,10);
        String destination = "La Plata";

        //Act
        when(hotelRepository.findHotelWithDateFromDateToAndDestination(from,to,destination)).thenReturn(obtainedHotelsEntity);
        ListHotelResponseDto obtainedListHotelResponseDTO = hotelService.getHotelesWithDateFromDateToAndDestination(from,to,destination);

        //Assert
        assertEquals(expectedListHotelResponseDto,obtainedListHotelResponseDTO);
    }
    @Test
    void shouldDeleteAnHotel(){
        //Arrange
        HotelEntity hotelEntity = HotelEntityFactory.newHotelEntity();
        HotelRequestDTO expectedHotelRequestDTO = HotelEntityFactory.newHotelRequestDTO();
        List<HotelBookingEntity> reservationList = new ArrayList<>();
        //Act
        when(hotelRepository.findByHotelCode(any())).thenReturn(Optional.of(hotelEntity));

        when(hotelRepository.findIfExisteBookings(any())).thenReturn(reservationList);

        HotelRequestDTO obtainedHotelRequestDTO = hotelService.deleteHotel(hotelEntity.getHotelCode());

        //Assert
        assertEquals(expectedHotelRequestDTO,obtainedHotelRequestDTO);
    }

    @Test
    void shouldReturnHotelsByDestinationRoomPriceSorted(){
        List<HotelResponseDTO> expectedHotelsList = new ArrayList<>();
        expectedHotelsList.add(HotelEntityFactory.newHotelResponseDTO());
        expectedHotelsList.add(HotelEntityFactory.newHotelResponseDTO());
        ListHotelResponseDto expectedList = new ListHotelResponseDto(expectedHotelsList);

        List<HotelEntity> toAddHotelEntityList = new ArrayList<>();
        toAddHotelEntityList.add(HotelEntityFactory.newHotelEntity());
        toAddHotelEntityList.add(HotelEntityFactory.newHotelEntity());

        when(hotelRepository.findByDestinationAndSortedByRoomPrice(any())).thenReturn(toAddHotelEntityList);

        ListHotelResponseDto obtainedList = hotelService.getHotelsByDestinationSortedByRoomPrice("La Plata");

        assertEquals(expectedList,obtainedList);

    }



}
