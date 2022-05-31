package com.santander.proyectofinal.repository;

import com.santander.proyectofinal.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserEntityRepository extends JpaRepository<UserEntity,Integer> {

    Optional<UserEntity> findByUsernameEquals(String username);


}
