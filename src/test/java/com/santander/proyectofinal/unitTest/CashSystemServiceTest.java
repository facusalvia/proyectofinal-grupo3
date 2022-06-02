package com.santander.proyectofinal.unitTest;

import com.santander.proyectofinal.dto.response.DayBenefitsResponseDTO;
import com.santander.proyectofinal.dto.response.MonthBenefitsResponseDTO;
import com.santander.proyectofinal.repository.IFlightReservationRepository;
import com.santander.proyectofinal.repository.IHotelBookingRepository;
import com.santander.proyectofinal.service.CashSystemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CashSystemServiceTest {

    @Mock
    IHotelBookingRepository hotelBookingRepository;

    @Mock
    IFlightReservationRepository flightReservationRepository;

    @InjectMocks
    CashSystemService cashSystemService;

    @Test
    void shouldReturnDayBenefits(){
        Double expectedCashAmountHotelBooking = 10D;
        Double expectedCashAmountFlightReservation = 10D;
        DayBenefitsResponseDTO expectedDayBenefitsResponseDTO = new DayBenefitsResponseDTO(LocalDate.now()
                ,expectedCashAmountHotelBooking+expectedCashAmountFlightReservation);

        when(hotelBookingRepository.obtainDailyBenefits(any())).thenReturn(expectedCashAmountHotelBooking);
        when(flightReservationRepository.obtainDailyBenefits(any())).thenReturn(expectedCashAmountFlightReservation);

        DayBenefitsResponseDTO obtainedDayBenefitsResponseDTO = cashSystemService.dayBenefits(LocalDate.now());

        assertEquals(expectedDayBenefitsResponseDTO,obtainedDayBenefitsResponseDTO);
    }

    @Test
    void shouldReturnMonthBenefits(){
        Integer month = 06;
        Integer year = 2022;
        Double expectedCashAmountHotelBooking = 10D;
        Double expectedCashAmountFlightReservation = 10D;

        MonthBenefitsResponseDTO expectedMonthBenefitsResponseDTO = new MonthBenefitsResponseDTO(month
                ,year,expectedCashAmountHotelBooking+expectedCashAmountFlightReservation);

        when(hotelBookingRepository.obtainMonthlyBenefits(any(),any())).thenReturn(expectedCashAmountHotelBooking);
        when(flightReservationRepository.obtainMonthlyBenefits(any(),any())).thenReturn(expectedCashAmountFlightReservation);

        MonthBenefitsResponseDTO obtainedMonthBenefitsResponseDTO = cashSystemService.monthBenefits(month,year);

        assertEquals(expectedMonthBenefitsResponseDTO,obtainedMonthBenefitsResponseDTO);

    }
}
