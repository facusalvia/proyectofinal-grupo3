package com.santander.proyectofinal.unitTest;

import com.lowagie.text.Document;
import com.santander.proyectofinal.entity.FlightReservationEntity;
import com.santander.proyectofinal.repository.IFlightEntityRepository;
import com.santander.proyectofinal.repository.IFlightReservationRepository;
import com.santander.proyectofinal.repository.IHotelBookingRepository;
import com.santander.proyectofinal.repository.IHotelRepository;
import com.santander.proyectofinal.service.FlightService;
import com.santander.proyectofinal.service.HotelService;
import com.santander.proyectofinal.service.PdfService;
import com.santander.proyectofinal.util.FlightReservationFactory;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PdfServiceTest {

    @Mock
    IHotelBookingRepository hotelBookingRepository;
    @Mock
    IFlightReservationRepository flightReservationRepository;
    @InjectMocks
    PdfService pdfService;


    @Test
    void shouldReturnAnAddedFlightReservation(){
        //Arrange
        FlightReservationEntity flightReservationEntity = FlightReservationFactory.newFlightReservationEntity();
        Document document = new Document();
        FileSystemResource expectedFile = new FileSystemResource(new File( "src/main/resources/FlightReservation.pdf"));
        //FileSystemResource obtainedFile = pdfService.exportFlightTicket(response,1);
        //Act
        when(flightReservationRepository.findById(any())).thenReturn(Optional.of(flightReservationEntity));

        //Assert
        //assertAll(flightReservationEntity,)
    }
}
