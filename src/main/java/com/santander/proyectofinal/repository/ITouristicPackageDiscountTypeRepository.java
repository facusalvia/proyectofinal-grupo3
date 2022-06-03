package com.santander.proyectofinal.repository;

import com.santander.proyectofinal.entity.TouristicPackageDiscountTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITouristicPackageDiscountTypeRepository extends JpaRepository<TouristicPackageDiscountTypeEntity, Integer> {
}
