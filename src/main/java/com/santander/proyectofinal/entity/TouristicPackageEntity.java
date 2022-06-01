package com.santander.proyectofinal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Table
@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class TouristicPackageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer packageNumber;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate creationDate;
    //private UserEntity user;
    @OneToMany(mappedBy = "touristicPackage", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<TouristicPackageBookingEntity> touristicPackageBookings;
    @OneToMany(mappedBy = "touristicPackage", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<TouristicPackageReservationEntity> touristicPackageReservations;


}
