package com.deepak.ml.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deepak.ml.dto.AuthRequest;
import com.deepak.ml.dto.CreateUserRequest;
import com.deepak.ml.dto.UserView;
import com.deepak.ml.model.ERole;
import com.deepak.ml.model.Role;
import com.deepak.ml.model.User;
import com.deepak.ml.repository.RoleRepository;
import com.deepak.ml.repository.UserRepository;
import com.deepak.ml.security.JwtTokenUtil;
import com.deepak.ml.services.UserDetailsImpl;
import com.deepak.ml.services.UserDetailsServiceImpl;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  	
  @Autowired 
  UserRepository userRepository;
  
  @Autowired
  JwtTokenUtil jwtUtils;

  @PostMapping("login")
  public ResponseEntity<User> login(@RequestBody @Valid AuthRequest request) {
      try {
          Authentication authenticate = authenticationManager
                  .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

          User user = (User) authenticate.getPrincipal();

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
      return userRepository.save(user);
  }

}