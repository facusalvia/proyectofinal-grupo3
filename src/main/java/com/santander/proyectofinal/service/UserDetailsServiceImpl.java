package com.santander.proyectofinal.service;

import com.santander.proyectofinal.dto.FlightDTO;
import com.santander.proyectofinal.dto.UserDTO;
import com.santander.proyectofinal.dto.UserDTOResponseProtected;
import com.santander.proyectofinal.dto.response.UserResponseDTO;
import com.santander.proyectofinal.entity.UserEntity;
import com.santander.proyectofinal.exceptions.RepositorySaveException;
import com.santander.proyectofinal.exceptions.UserAlreadyExistsException;
import com.santander.proyectofinal.exceptions.UserDoesNotExistException;
import com.santander.proyectofinal.exceptions.flightException.FlightNoAvailableException;
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
import java.util.stream.Collectors;

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

        return new User(userEntity.getUsername(), userEntity.getPassword(), true, true, true, userEntity.isAccountNonLocked(), grantedAuthorities);
    }


    public UserDTO add(UserDTO userDTO) {
       if(userEntityRepository.findByUsernameEquals(userDTO.getUsername()).isPresent()){
           throw new UserAlreadyExistsException();
               }
       UserEntity addUserEntity = modelMapper.map(userDTO, UserEntity.class);
       addUserEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        addUserEntity.setAccountNonLocked(true);
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

    public UserDTO userLocked(String username) {
        UserEntity userEntity = userEntityRepository.findByUsernameEquals(username).orElseThrow(UserDoesNotExistException::new);
        userEntity.setAccountNonLocked(false);
        userEntityRepository.save(userEntity);
        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
        return userDTO;
    }
    public UserDTO userUnlocked(String username) {
        UserEntity userEntity = userEntityRepository.findByUsernameEquals(username).orElseThrow(UserDoesNotExistException::new);
        userEntity.setAccountNonLocked(true);
        userEntityRepository.save(userEntity);
        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
        return userDTO;
    }
    public List<UserDTOResponseProtected> userList() {
        List<UserEntity> userEntityList = userEntityRepository.findAll();
        if (userEntityList.isEmpty())
            throw new UserDoesNotExistException();
        List<UserDTOResponseProtected> userResponseDTOList = userEntityList.stream().map(
                        user -> modelMapper.map(user, UserDTOResponseProtected.class)
                )
                .collect(Collectors.toList());
        return userResponseDTOList;
    }

}
