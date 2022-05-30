package com.santander.proyectofinal.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "hotels")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class HotelEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Column
    private String hotelCode;
    @Column
    private String name;
    @Column
    private String place;
    @Column
    private String roomType;
    @Column
    private Double roomPrice;
    @Column
    private LocalDate disponibilityDateFrom;
    @Column
    private LocalDate disponibilityDateTo;
    @Column
    private Boolean isBooking;
}
