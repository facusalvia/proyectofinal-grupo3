package com.santander.proyectofinal.repository;

import com.santander.proyectofinal.entity.FlightEntity;
import com.santander.proyectofinal.entity.FlightReservationEntity;
import com.santander.proyectofinal.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IFlightEntityRepository extends JpaRepository<FlightEntity, Integer> {

    FlightEntity save(FlightEntity flightEntity);

   List<FlightEntity> findAllByDateFromLessThanEqualAndDateToGreaterThanEqualAndOriginEqualsAndDestinyEquals(LocalDate dateFrom, LocalDate dateTo,String origin,String destiny);

    Optional<FlightEntity> findByFlightNumberEquals(String username);

    Optional<FlightEntity> findById(Integer id);

    List<FlightEntity> findAll();

    @Query("SELECT f.flightReservationEntityList FROM FlightEntity f JOIN FlightReservationEntity fr ON f.id = fr.flightEntity.id WHERE f.flightNumber = :flightNumber AND fr.isActive = true")
    List<FlightReservationEntity> findIfExistReservation(@Param("flightNumber") String flightNumber);

}
