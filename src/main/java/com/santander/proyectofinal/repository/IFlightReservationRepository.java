package com.santander.proyectofinal.repository;

import com.santander.proyectofinal.dto.FlightReservationDTO;
import com.santander.proyectofinal.entity.FlightReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IFlightReservationRepository extends JpaRepository<FlightReservationEntity, Integer> {
    @Query("SELECT SUM(fr.totalAmount) FROM FlightReservationEntity fr WHERE fr.createdAt=:date AND fr.isActive=TRUE")
    Double obtainDailyBenefits(@Param("date") LocalDate date);
    @Query("SELECT SUM(fr.totalAmount) FROM FlightReservationEntity fr WHERE year(fr.createdAt)=:year AND month(fr.createdAt)=:month AND fr.isActive=TRUE")
    Double obtainMonthlyBenefits(@Param("month") Integer month,@Param("year") Integer year);

    List<FlightReservationEntity> findByIsActiveTrue();

    @Query("FROM FlightReservationEntity AS fr WHERE fr.flightEntity.id IN (SELECT id FROM FlightEntity AS fe WHERE fe.flightNumber = :flightNumber) ")
    List<FlightReservationEntity> findByFlightNumber(@Param("flightNumber") String flightNumber);
}
