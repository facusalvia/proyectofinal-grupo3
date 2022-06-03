package com.santander.proyectofinal.repository;

import com.santander.proyectofinal.entity.FlightReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface IFlightReservationRepository extends JpaRepository<FlightReservationEntity, Integer> {
    @Query("SELECT SUM(fr.totalAmount) FROM FlightReservationEntity fr WHERE fr.createdAt=:date")
    Double obtainDailyBenefits(@Param("date") LocalDate date);
    @Query("SELECT SUM(fr.totalAmount) FROM FlightReservationEntity fr WHERE year(fr.createdAt)=:year AND month(fr.createdAt)=:month")
    Double obtainMonthlyBenefits(@Param("month") Integer month,@Param("year") Integer year);

    List<FlightReservationEntity> findByIsActiveTrue();


    @Query("SELECT fr FROM FlightReservationEntity fr WHERE fr.isActive = false AND year(fr.canceledAt) = :year ORDER BY month(fr.canceledAt)")
    List<FlightReservationEntity> findCanceledFlightsReservationsInYearForMonth(@Param("year") Integer year);
}
