package com.santander.proyectofinal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate disponibilityDateFrom;
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate disponibilityDateTo;
    @Column
    private Boolean isBooking;
    @OneToMany(mappedBy = "hotel")
    private List<HotelBookingEntity> hotelBookingEntityList;
}
