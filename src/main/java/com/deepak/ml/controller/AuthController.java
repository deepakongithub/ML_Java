package com.deepak.ml.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.deepak.ml.dto.AuthRequest;
import com.deepak.ml.dto.CreateUserRequest;
import com.deepak.ml.model.ERole;
import com.deepak.ml.model.Role;
import com.deepak.ml.model.User;
import com.deepak.ml.repository.RoleRepository;
import com.deepak.ml.repository.UserRepository;
import com.deepak.ml.security.JwtTokenUtil;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600 ,exposedHeaders = "*")
@RestController
@RequestMapping("/api/auth/")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  	
  @Autowired 
  UserRepository userRepository;
  
  @Autowired
  JwtTokenUtil jwtUtils;

  @Autowired
  RoleRepository roleRepository;
  
  @PostMapping("login")
  public ResponseEntity<UserDetails> login(@RequestBody  AuthRequest request) {
      try {
          Authentication authenticate = authenticationManager
                  .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));


          UserDetails user = (UserDetails) authenticate.getPrincipal();
    	  System.out.println("inside Logger"+user.getUsername());


          return ResponseEntity.ok()
                  .header(HttpHeaders.AUTHORIZATION, jwtUtils.generateAccessToken(user))
                  .body(user);
      } catch (BadCredentialsException ex) {
          return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
      }
  }

  @PostMapping("register")
  public User register(@RequestBody @Valid CreateUserRequest request) {
	  User user = new User();
	  
	  user.setCreatedAt(LocalDateTime.now());
	  user.setEmail(request.getUsername());
	  user.setEnabled(true);
	  user.setModifiedAt(LocalDateTime.now());
	  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	  String testPasswordEncoded = passwordEncoder.encode(request.getPassword());
	  System.out.println("encoded password = "+ testPasswordEncoded);
	  user.setPassword(testPasswordEncoded);
	  Optional<Role> role = roleRepository.findByName(ERole.ROLE_ADMIN);
 	  Set<Role> roles = new HashSet<Role>();
 	  roles.add(role.get());
	  user.setRoles(roles);
	  user.setUsername(request.getUsername());
	  user.setFullName(request.getFullName());
	  
	  //return user;
      return userRepository.save(user);
  }

}