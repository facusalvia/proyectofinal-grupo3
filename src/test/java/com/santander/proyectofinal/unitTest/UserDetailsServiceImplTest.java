package com.santander.proyectofinal.unitTest;

import com.santander.proyectofinal.entity.UserEntity;
import com.santander.proyectofinal.exceptions.PaymentMethodDebitCanNotMoreThanOneDueException;
import com.santander.proyectofinal.repository.IUserEntityRepository;
import com.santander.proyectofinal.service.UserDetailsServiceImpl;
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


        UserEntity expectedUserEntity = new UserEntity(1,"Juan","1234","manager");
        String userNameToAdd = "Juan";

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
        UserDetails expectedUser = new User(expectedUserEntity.getUsername(),"ABC",grantedAuthorities);

        when(userEntityRepository.findByUsernameEquals(any())).thenReturn(Optional.of(expectedUserEntity));
        when(passwordEncoder.encode("1234")).thenReturn("ABC");

        UserDetails obtainedUser = userDetailsService.loadUserByUsername(userNameToAdd);

        assertEquals(expectedUser,obtainedUser);

    }


}
