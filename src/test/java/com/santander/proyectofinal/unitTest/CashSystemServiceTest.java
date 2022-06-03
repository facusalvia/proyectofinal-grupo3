package com.santander.proyectofinal.unitTest;

import com.santander.proyectofinal.dto.response.DayBenefitsResponseDTO;
import com.santander.proyectofinal.dto.response.HotelMonthBenefitsResponseDTO;
import com.santander.proyectofinal.dto.response.HotelYearBenefitsResponseDTO;
import com.santander.proyectofinal.dto.response.MonthBenefitsResponseDTO;
import com.santander.proyectofinal.entity.HotelEntity;
import com.santander.proyectofinal.exceptions.QueryDidNotReturnAnyResult;
import com.santander.proyectofinal.repository.IFlightReservationRepository;
import com.santander.proyectofinal.repository.IHotelBookingRepository;
import com.santander.proyectofinal.service.CashSystemService;
import com.santander.proyectofinal.util.HotelEntityFactory;
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

    @Test
    void shouldReturnHotelMonthIncome(){
        Integer month = 06;
        Integer year = 2022;
        Double expectedCashAmountHotel = 100D;

        HotelEntity mockedHotelEntity = HotelEntityFactory.newHotelEntity();

        HotelMonthBenefitsResponseDTO expectedHotelMonthBenefitResponseDTO =
                new  HotelMonthBenefitsResponseDTO(month, year, expectedCashAmountHotel, mockedHotelEntity.getHotelCode());

        when(hotelBookingRepository.obtainMonthlyBenefits(mockedHotelEntity.getHotelCode(), year, month)).thenReturn(expectedCashAmountHotel);

        HotelMonthBenefitsResponseDTO obtainedHotelMonthBenefitResponseDTO = cashSystemService.hotelMonthBenefits(mockedHotelEntity.getHotelCode(), year, month);

        assertEquals(expectedHotelMonthBenefitResponseDTO, obtainedHotelMonthBenefitResponseDTO);
    }

    @Test
    void shouldReturnQueryDidNotHaveResultsOnWrongHotelCode(){
        Integer month = 6;
        Integer year = 2022;

        when(hotelBookingRepository.obtainMonthlyBenefits("codigoErroneo", year, month)).thenReturn(null);

        assertThrows(QueryDidNotReturnAnyResult.class,()-> cashSystemService.hotelMonthBenefits("codigoErroneo", year, month));
    }

    @Test
    void shouldReturnHotelYearIncome(){
        Integer year = 2022;
        Double expectedCashAmountHotel = 100D;

        HotelEntity mockedHotelEntity = HotelEntityFactory.newHotelEntity();

        HotelYearBenefitsResponseDTO expectedHotelYearBenefitResponseDTO =
                new  HotelYearBenefitsResponseDTO(year, expectedCashAmountHotel, mockedHotelEntity.getHotelCode());

        when(hotelBookingRepository.obtainHotelYearBenefits(mockedHotelEntity.getHotelCode(), year)).thenReturn(expectedCashAmountHotel);

        HotelYearBenefitsResponseDTO obtainedHotelYearBenefitsResponseDTO = cashSystemService.hotelYearBenefits(mockedHotelEntity.getHotelCode(), year);

        assertEquals(expectedHotelYearBenefitResponseDTO, obtainedHotelYearBenefitsResponseDTO);
    }
}
