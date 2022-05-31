package com.santander.proyectofinal.repository;

import com.santander.proyectofinal.dto.FlightReservationDTO;
import com.santander.proyectofinal.entity.FlightReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFlightReservationRepository extends JpaRepository<FlightReservationEntity, Integer> {
}
