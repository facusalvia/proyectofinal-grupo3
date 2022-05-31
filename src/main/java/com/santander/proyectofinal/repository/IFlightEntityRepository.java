package com.santander.proyectofinal.repository;

import com.santander.proyectofinal.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
