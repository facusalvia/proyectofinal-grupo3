package com.santander.proyectofinal.service;

import com.santander.proyectofinal.dto.response.DayBenefitsResponseDTO;
import com.santander.proyectofinal.dto.response.MonthBenefitsResponseDTO;
import com.santander.proyectofinal.repository.IFlightReservationRepository;
import com.santander.proyectofinal.repository.IHotelBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CashSystemService {
    @Autowired
    IHotelBookingRepository hotelBookingRepository;

    @Autowired
    IFlightReservationRepository flightReservationRepository;


    public DayBenefitsResponseDTO dayBenefits(LocalDate parcedDate) {
        Double cashAmountHotelBooking = hotelBookingRepository.obtainDailyBenefits(parcedDate);
        if(cashAmountHotelBooking==null){cashAmountHotelBooking=0D;}
        Double cashAmountFlightReservation = flightReservationRepository.obtainDailyBenefits(parcedDate);
        if(cashAmountFlightReservation==null){cashAmountFlightReservation=0D;}
        Double cashAmount = cashAmountHotelBooking + cashAmountFlightReservation;
        return new DayBenefitsResponseDTO(parcedDate, cashAmount);
    }

    public MonthBenefitsResponseDTO monthBenefits(Integer month, Integer year) {
        Double cashAmountHotelBooking = hotelBookingRepository.obtainMonthlyBenefits(month,year);
        if(cashAmountHotelBooking==null){cashAmountHotelBooking=0D;}
        Double cashAmountFlightReservation = flightReservationRepository.obtainMonthlyBenefits(month,year);
        if(cashAmountFlightReservation==null){cashAmountFlightReservation=0D;}
        Double cashAmount = cashAmountHotelBooking + cashAmountFlightReservation;
        return new MonthBenefitsResponseDTO(month,year,cashAmount);
    }
}
