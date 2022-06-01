package com.santander.proyectofinal.repository;

import com.santander.proyectofinal.entity.TouristicPackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITouristicPackageRepository extends JpaRepository<TouristicPackageEntity, Integer> {
}
