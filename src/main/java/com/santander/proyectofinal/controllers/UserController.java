package com.santander.proyectofinal.controllers;

import com.santander.proyectofinal.config.JwtUtils;
import com.santander.proyectofinal.dto.FlightDTO;
import com.santander.proyectofinal.dto.SuccessDTO;
import com.santander.proyectofinal.dto.TaskMessage;
import com.santander.proyectofinal.dto.UserDTO;
import com.santander.proyectofinal.dto.request.UserRequestDTO;
import com.santander.proyectofinal.dto.response.UserResponseDTO;
import com.santander.proyectofinal.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserRequestDTO authenticationRequest)
            throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        String token = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new UserResponseDTO(token));
    }

    @PostMapping("/new")
    public ResponseEntity<TaskMessage> addUser(@Valid @RequestBody UserDTO userDTO) {
        userDetailsService.add(userDTO);
        return ResponseEntity.ok().body(new TaskMessage("Se agrego un nuevo Usuario", 201));
    }

    @PutMapping(value = "/edit", params = {"id"})
    public ResponseEntity<TaskMessage> updateUser(@Valid @RequestParam(value = "id") Integer id, @RequestBody UserDTO userDTO) {
        userDetailsService.update(id, userDTO);
        return ResponseEntity.ok().body(new TaskMessage("Se modifico correctamente el Usuario", 200));
    }
    @DeleteMapping(value ="/delete", params= {"id"})
    public ResponseEntity<SuccessDTO> deleteUser(@RequestParam(value="id") Integer id){
        userDetailsService.delete(id);
        return ResponseEntity.ok().body(new SuccessDTO( "Usuario dado de baja correctamente" , 200));
    }

}
