package com.santander.proyectofinal.repository;

import com.santander.proyectofinal.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IFlightEntityRepository extends JpaRepository<FlightEntity,Integer> {
    FlightEntity save(FlightEntity flightEntity);
}
