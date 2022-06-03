package com.santander.proyectofinal.repository;

import com.santander.proyectofinal.entity.HotelBookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IHotelBookingRepository extends JpaRepository<HotelBookingEntity, Integer> {

    List<HotelBookingEntity> findByIsActiveTrue();

    @Query("SELECT SUM(hb.totalAmount) FROM HotelBookingEntity hb WHERE hb.createdAt=:date")
    Double obtainDailyBenefits(@Param("date") LocalDate date);
    @Query("SELECT SUM(hb.totalAmount) FROM HotelBookingEntity hb WHERE year(hb.createdAt)=:year AND month(hb.createdAt)=:month")
    Double obtainMonthlyBenefits(@Param("month") Integer month,@Param("year") Integer year);

    //TODO: no deberia tener en cuenta reservas canceladas
    @Query("SELECT SUM(hb.totalAmount) FROM HotelBookingEntity hb WHERE year(hb.createdAt)=:year AND month(hb.createdAt)=:month AND hb.hotel.hotelCode = :hotelCode")
    Double obtainMonthlyBenefits(String hotelCode, @Param("year") Integer year, @Param("month") Integer month);

    @Query("SELECT SUM(hb.totalAmount) FROM HotelBookingEntity hb WHERE year(hb.createdAt)=:year AND hb.hotel.hotelCode = :hotelCode")
    Double obtainHotelYearBenefits(String hotelCode, Integer year);

    //@Query("FROM HotelEntity h WHERE h.disponibilityDateFrom <= :dateFrom AND h.disponibilityDateTo >= :dateTo AND h.place = :destination")
    List<HotelBookingEntity> findHotelBookingsByHotelAndDateFromAndDateTo(String hotelCode, LocalDate from, LocalDate to);
}
