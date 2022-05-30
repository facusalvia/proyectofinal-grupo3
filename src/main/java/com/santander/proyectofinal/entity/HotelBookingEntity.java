package com.santander.proyectofinal.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "hotel_bookings")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class HotelBookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //@Column
    //private UserEntity user;
    @Column
    private LocalDate dateFrom;
    @Column
    private LocalDate dateTo;
    @Column
    private String destination;
    @ManyToOne
    private HotelEntity hotel;
    @Column
    private Integer peopleAmount;
    @Column
    private String roomType;
    @ManyToMany(mappedBy = "hotelBookingEntity", cascade = CascadeType.PERSIST)
    private List<GuestEntity> people;
    //@Column
    //private PaymentMethodEntity paymentMethod;
}
