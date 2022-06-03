package com.santander.proyectofinal.repository;

import com.santander.proyectofinal.entity.ClientEntity;
import com.santander.proyectofinal.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface IClientRepository extends JpaRepository<ClientEntity, Integer> {
    Optional<ClientEntity> findByUsernameEquals(String username);
    @Query("SELECT new map(c as cli,SUM(c.bookings.size)+SUM(c.reservations.size) as total )FROM ClientEntity as c group by c.id order by total desc ")
    List<Map<ClientEntity,Integer>> findCLientsTop3();
}
