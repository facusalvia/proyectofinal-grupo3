package com.santander.proyectofinal.util;

import com.santander.proyectofinal.dto.UserDTO;
import com.santander.proyectofinal.entity.UserEntity;

public class UserEntityFactory {

    public static UserEntity newUserEntity(){
        return new UserEntity(1,"username", "123", "manager");
    }
    public static UserDTO newUserDTO(){
        return new UserDTO("username", "123", "manager");
    }
}
