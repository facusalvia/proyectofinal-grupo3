package com.santander.proyectofinal.service;

import com.santander.proyectofinal.repository.IUserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {

    @Autowired
    IUserEntityRepository userEntityRepository;

}
