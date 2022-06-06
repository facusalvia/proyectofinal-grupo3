package com.santander.proyectofinal.repository;

import com.santander.proyectofinal.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface IClientRepository extends JpaRepository<ClientEntity, Integer> {
    Optional<ClientEntity> findByUsernameEquals(String username);
    @Query("SELECT new map(c as cli,SUM(c.bookings.size)+SUM(c.reservations.size) as total )FROM ClientEntity as c group by c.id order by total desc ")
    List<Map<ClientEntity,Integer>> findCLientsTop3();

    @Query(value = "SELECT c.id,c.username,c.name,c.lastname,count(f.client_id) max FROM clients c JOIN flights_reservation f ON f.client_id=c.id group by f.client_id order by max desc limit 1",nativeQuery = true)
    ClientEntity findClientWithMoreFlightReservation();

    @Query(value = "SELECT c.id,c.username,c.name name,c.lastname lastname,count(h.client_id) max FROM clients c JOIN hotel_bookings h ON h.client_id=c.id group by h.client_id order by max desc limit 1",nativeQuery = true)
    ClientEntity  findClientWithMoreFlightBookings();


}
