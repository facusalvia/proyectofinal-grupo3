package com.santander.proyectofinal.repository;

import com.santander.proyectofinal.dto.FlightReservationDTO;
import com.santander.proyectofinal.entity.FlightReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface IFlightReservationRepository extends JpaRepository<FlightReservationEntity, Integer> {
    @Query("SELECT SUM(fr.totalAmount) FROM FlightReservationEntity fr WHERE fr.createdAt=:date")
    Double obtainDailyBenefits(@Param("date") LocalDate date);
    @Query("SELECT SUM(fr.totalAmount) FROM FlightReservationEntity fr WHERE year(fr.createdAt)=:year AND month(fr.createdAt)=:month")
    Double obtainMonthlyBenefits(@Param("month") Integer month,@Param("year") Integer year);
}
