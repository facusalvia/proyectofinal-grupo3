package com.santander.proyectofinal.repository;

import com.santander.proyectofinal.entity.HotelBookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IHotelBookingRepository extends JpaRepository<HotelBookingEntity, Integer> {

    List<HotelBookingEntity> findByIsActiveTrue();
}
