package com.santander.proyectofinal.repository;

import com.santander.proyectofinal.entity.TouristicPackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITouristicPackageRepository extends JpaRepository<TouristicPackageEntity, Integer> {
    Optional<TouristicPackageEntity> findByPackageNumber(Integer packageNumber);

}
