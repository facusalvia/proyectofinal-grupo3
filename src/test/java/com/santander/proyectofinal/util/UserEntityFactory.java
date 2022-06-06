package com.santander.proyectofinal.util;

import com.santander.proyectofinal.dto.UserDTO;
import com.santander.proyectofinal.dto.UserDTOResponseProtected;
import com.santander.proyectofinal.dto.request.UserRequestDTO;
import com.santander.proyectofinal.entity.UserEntity;

public class UserEntityFactory {

    public static UserEntity newUserEntity(){
        return new UserEntity(1,"username", "123", "manager", true);
    }
    public static UserDTO newUserDTO(){
        return new UserDTO("username", "123", "manager");
    }
    public static UserDTOResponseProtected newUserDTOProtected(){
        return new UserDTOResponseProtected(1,"username", "manager",true);
    }
    public static UserRequestDTO newUserRequestDTO(){
        return new UserRequestDTO("username", "123");
    }
}
