package com.santander.proyectofinal.entity;

import com.santander.proyectofinal.entity.HotelBookingEntity;
import com.santander.proyectofinal.entity.TouristicPackageEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity
@Table
public class TouristicPackageBookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="fk_package_id", referencedColumnName = "id")
    private TouristicPackageEntity touristicPackage;

    @ManyToOne
    @JoinColumn(name="fk_booking_id", referencedColumnName = "id")
    private HotelBookingEntity hotelBooking;
}
