package com.santander.proyectofinal.repository;

import com.santander.proyectofinal.entity.HotelEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface IHotelRepository extends JpaRepository<HotelEntity, Integer> {
    Optional<HotelEntity> findByHotelCode(String hotelCode);

    //TODO: mejorar criterio de búsqueda por fechas

    @Query("FROM HotelEntity h WHERE h.disponibilityDateFrom >= :dateFrom AND h.disponibilityDateTo <= :dateTo AND h.place = :destination")
    List<HotelEntity> findHotelWithDateFromDateToAndDestination(
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo,
            @Param("destination") String destination);


}
