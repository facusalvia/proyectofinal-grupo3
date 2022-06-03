package com.santander.proyectofinal.repository;

import com.santander.proyectofinal.entity.TouristicPackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ITouristicPackageRepository extends JpaRepository<TouristicPackageEntity, Integer> {
    Optional<TouristicPackageEntity> findByPackageNumberEquals(Integer packageNumber);

    @Query("FROM TouristicPackageEntity AS tpe WHERE tpe.id IN(SELECT touristicPackage.id FROM TouristicPackageBookingEntity AS tpbe WHERE tpbe.hotelBooking.id = :id)")
    List<TouristicPackageEntity> findPackagesByHotelBooking(@Param("id") Integer id);

    @Query("FROM TouristicPackageEntity AS tpe WHERE tpe.id IN(SELECT touristicPackage.id FROM TouristicPackageReservationEntity AS tpre WHERE tpre.flightReservation.id = :id)")
    List<TouristicPackageEntity> findPackagesByFlightReservation(@Param("id") Integer id);
}
