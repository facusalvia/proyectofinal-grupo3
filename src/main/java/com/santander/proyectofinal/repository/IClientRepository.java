package com.santander.proyectofinal.repository;

import com.santander.proyectofinal.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IClientRepository extends JpaRepository<ClientEntity, Integer> {
    Optional<ClientEntity> findByUsernameEquals(String username);
}
