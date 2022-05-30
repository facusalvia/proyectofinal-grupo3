package com.santander.proyectofinal.repository;

import com.santander.proyectofinal.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHotelRepository extends JpaRepository<HotelEntity, Integer> {
}
