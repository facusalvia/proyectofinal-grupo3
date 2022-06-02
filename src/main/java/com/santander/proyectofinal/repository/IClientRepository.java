package com.santander.proyectofinal.repository;

import com.santander.proyectofinal.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientRepository extends JpaRepository<ClientEntity, Integer> {
}
