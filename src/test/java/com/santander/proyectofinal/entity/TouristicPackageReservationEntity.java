package com.santander.proyectofinal.entity;

import com.santander.proyectofinal.entity.FlightReservationEntity;
import com.santander.proyectofinal.entity.TouristicPackageEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table
@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class TouristicPackageReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="fk_package_id", referencedColumnName = "id")
    private TouristicPackageEntity touristicPackage;

    @ManyToOne
    @JoinColumn(name="fk_reservation_id", referencedColumnName = "id")
    private FlightReservationEntity flightReservation;
}
