package com.santander.proyectofinal.repository;

import com.santander.proyectofinal.entity.HotelBookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHotelBookingRepository extends JpaRepository<HotelBookingEntity, Integer> {
}
