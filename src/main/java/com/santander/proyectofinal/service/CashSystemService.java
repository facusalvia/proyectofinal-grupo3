package com.santander.proyectofinal.service;

import com.santander.proyectofinal.dto.response.DayBenefitsResponseDTO;
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
        Double cashAmountFlightReservation = flightReservationRepository.obtainDailyBenefits(parcedDate);
        Double cashAmount = cashAmountHotelBooking + cashAmountFlightReservation;
        return new DayBenefitsResponseDTO(parcedDate,cashAmount);
    }

   //public Object monthBenefits(Integer month, Integer year) {

   //}
}
