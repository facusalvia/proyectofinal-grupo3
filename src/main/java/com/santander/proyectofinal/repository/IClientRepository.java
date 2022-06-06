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

    @Query(value = "Select id,username,name,lastname,max(max) maximo FROM (SELECT cl.id id,cl.username username,cl.name name,cl.lastname lastname,count(f.client_id) max FROM clients cl JOIN flights_reservation f ON f.client_id=cl.id group by f.client_id) a",nativeQuery = true)
    ClientEntity findClientWithMoreFlightReservation();


}
