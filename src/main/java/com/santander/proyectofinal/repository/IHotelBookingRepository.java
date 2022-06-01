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
}
