package com.santander.proyectofinal.unitTest;

import com.santander.proyectofinal.dto.FlightDTO;
import com.santander.proyectofinal.dto.UserDTO;
import com.santander.proyectofinal.dto.UserDTOResponseProtected;
import com.santander.proyectofinal.entity.FlightEntity;
import com.santander.proyectofinal.entity.UserEntity;
import com.santander.proyectofinal.exceptions.PaymentMethodDebitCanNotMoreThanOneDueException;
import com.santander.proyectofinal.repository.IUserEntityRepository;
import com.santander.proyectofinal.service.UserDetailsServiceImpl;
import com.santander.proyectofinal.util.FlightEntityFactory;
import com.santander.proyectofinal.util.UserEntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceImplTest {

    @Mock
    IUserEntityRepository userEntityRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    UserDetailsServiceImpl userDetailsService;


    @Test
    void shouldReturnAnUserDetails(){


        UserEntity expectedUserEntity = new UserEntity(1,"Juan","1234","manager", true);
        String userNameToAdd = "Juan";

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
        UserDetails expectedUser = new User(expectedUserEntity.getUsername(),"ABC",grantedAuthorities);

        when(userEntityRepository.findByUsernameEquals(any())).thenReturn(Optional.of(expectedUserEntity));
        //when(passwordEncoder.encode("1234")).thenReturn("ABC");

        UserDetails obtainedUser = userDetailsService.loadUserByUsername(userNameToAdd);

        assertEquals(expectedUser,obtainedUser);

    }

    @Test
    void shouldReturnAnAddedUser(){
        //Arrange
        UserDTO userDTOToAdd = UserEntityFactory.newUserDTO();
        UserEntity userEntity = UserEntityFactory.newUserEntity();
        //Act
        when(userEntityRepository.findByUsernameEquals(any())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(any())).thenReturn("pass");
        when(userEntityRepository.save(any())).thenReturn(userEntity);

        UserDTO addedUser = userDetailsService.add(userDTOToAdd);
        //Assert
        assertAll(()->assertEquals(userDTOToAdd,addedUser));
    }

    @Test
    void shouldUpdateUser(){
        //Arrange
        UserDTO userDTOToAdd = UserEntityFactory.newUserDTO();
        UserEntity userEntity = UserEntityFactory.newUserEntity();
        //Act
        when(userEntityRepository.findById(any())).thenReturn(Optional.of(userEntity));
        when(passwordEncoder.encode(any())).thenReturn("pass");
        when(userEntityRepository.save(any())).thenReturn(userEntity);
        when(userEntityRepository.findByUsernameEquals(any())).thenReturn(Optional.empty());

        UserDTO addedUser = userDetailsService.update(1,userDTOToAdd);
        //Assert
        assertAll(()->assertEquals(userDTOToAdd,addedUser));
    }

    @Test
    void shoulDeleteUser(){
        //Arrange
        UserDTO userDTOToAdd = UserEntityFactory.newUserDTO();
        UserEntity userEntity = UserEntityFactory.newUserEntity();
        //Act
        when(userEntityRepository.findById(any())).thenReturn(Optional.of(userEntity));

        UserDTO addedUser = userDetailsService.delete(1);
        //Assert
        assertAll(()->assertEquals(userDTOToAdd,addedUser));
    }

    @Test
    void shouldReturnAllUser(){
        //Arrange
        List<UserDTOResponseProtected> userDTOResponseProtectedList = new ArrayList<>();
        userDTOResponseProtectedList.add(UserEntityFactory.newUserDTOProtected());
        List<UserEntity> userEntityList = new ArrayList<>();
        userEntityList.add(UserEntityFactory.newUserEntity());
        //Act
        when(userEntityRepository.findAll()).thenReturn(userEntityList);

        List<UserDTOResponseProtected> userDTOListObtained = userDetailsService.userList();
        //Assert
        assertAll(()->assertEquals(userDTOResponseProtectedList,userDTOListObtained));
    }

}
