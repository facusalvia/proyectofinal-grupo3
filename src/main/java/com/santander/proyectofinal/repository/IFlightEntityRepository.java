package com.santander.proyectofinal.repository;

import com.santander.proyectofinal.entity.FlightEntity;
import com.santander.proyectofinal.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IFlightEntityRepository extends JpaRepository<FlightEntity,Integer> {

    FlightEntity save(FlightEntity flightEntity);
    Optional<FlightEntity> update(FlightEntity flightEntity);
    Optional<FlightEntity> findByFlightNumberEquals(String username);

    List<FlightEntity> getFlights();
}
