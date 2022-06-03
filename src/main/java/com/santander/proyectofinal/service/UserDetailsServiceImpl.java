package com.santander.proyectofinal.service;

import com.santander.proyectofinal.dto.UserDTO;
import com.santander.proyectofinal.entity.UserEntity;
import com.santander.proyectofinal.exceptions.RepositorySaveException;
import com.santander.proyectofinal.exceptions.UserAlreadyExistsException;
import com.santander.proyectofinal.exceptions.UserDoesNotExistException;
import com.santander.proyectofinal.repository.IUserEntityRepository;
import org.modelmapper.ModelMapper;
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

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    IUserEntityRepository userEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userEntityRepository.findByUsernameEquals(username).orElseThrow(()-> {throw new UsernameNotFoundException("User not found");} );
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (userEntity.getRol().equals("manager")) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
        }
        if (userEntity.getRol().equals("employee")) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_EMPLOYEE"));
        }

        return new User(userEntity.getUsername(), userEntity.getPassword(), grantedAuthorities);
    }


    public UserDTO add(UserDTO userDTO) {
       if(userEntityRepository.findByUsernameEquals(userDTO.getUsername()).isPresent()){
           throw new UserAlreadyExistsException();
               }
       UserEntity addUserEntity = modelMapper.map(userDTO, UserEntity.class);
       addUserEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        addUserEntity = userEntityRepository.save(addUserEntity);
        if(addUserEntity.getId() == null){
            throw new RepositorySaveException();
        }
       return userDTO;
    }

    public UserDTO update(Integer id, UserDTO userDTO) {
        UserEntity userEntity = userEntityRepository.findById(id).orElseThrow(UserDoesNotExistException::new);
        userEntity.setUsername(null);
        userEntityRepository.save(userEntity);
        if(userEntityRepository.findByUsernameEquals(userDTO.getUsername()).isPresent()){
            throw new UserAlreadyExistsException();
        }

        UserEntity addUserEntity = modelMapper.map(userDTO, UserEntity.class);
        addUserEntity.setId(id);
        addUserEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userEntityRepository.save(addUserEntity);

        return userDTO;
    }

    public UserDTO delete(Integer id) {
        UserEntity userEntity = userEntityRepository.findById(id).orElseThrow(UserDoesNotExistException::new);

        userEntityRepository.delete(userEntity);

        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
        return userDTO;
    }
}
