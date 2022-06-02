package com.santander.proyectofinal.service;

import com.santander.proyectofinal.entity.UserEntity;
import com.santander.proyectofinal.repository.IUserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    IUserEntityRepository userEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userEntityRepository.findByUsernameEquals(username).orElseThrow(()-> {throw new UsernameNotFoundException("User not found");} );
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (userEntity.getRol().equals("manager")) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        if (userEntity.getRol().equals("employee")) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return new User(userEntity.getUsername(), passwordEncoder.encode(userEntity.getPassword()), grantedAuthorities);
    }
}
