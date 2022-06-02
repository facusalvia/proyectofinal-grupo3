package unitTest;

import com.santander.proyectofinal.dto.request.HotelRequestDTO;
import com.santander.proyectofinal.entity.HotelBookingEntity;
import com.santander.proyectofinal.entity.HotelEntity;
import com.santander.proyectofinal.repository.IHotelRepository;
import com.santander.proyectofinal.service.HotelService;
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

        List<HotelBookingEntity> listBookings = new ArrayList<>();
        HotelEntity hotelEntity = new HotelEntity(1,"NUEVO-001","Hotel Nuevo",
                "La Plata","Double",6200.0,LocalDate.of(2022,06,05),
                LocalDate.of(2022,06,10),false,listBookings);

        HotelRequestDTO hotelToAdd= new HotelRequestDTO("NUEVO-001","Hotel Nuevo",
                "La Plata","Double",6200.0,LocalDate.of(2022,06,05),
                LocalDate.of(2022,06,10),false);
        HotelEntity mockedHotel = new HotelEntity(1,"NUEVO-001","Hotel Nuevo",
                "La Plata","Double",6200.0,LocalDate.of(2022,06,05),
                LocalDate.of(2022,06,10),false,listBookings);

        when(hotelRepository.findByHotelCode(hotelToAdd.getHotelCode())).thenReturn(Optional.empty());
        when(hotelRepository.save(any())).thenReturn(mockedHotel);

        HotelRequestDTO addedHotel = hotelService.addHotel(hotelToAdd);
        assertAll(()-> assertEquals(hotelToAdd,addedHotel));

    }

}
