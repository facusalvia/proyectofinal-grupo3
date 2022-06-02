package com.santander.proyectofinal.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "touristic_package_discounts")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class TouristicPackageDiscountTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double discount;
    @OneToMany(mappedBy = "touristicPackageDiscountType")
    private List<TouristicPackageEntity> touristicPackage;
}
