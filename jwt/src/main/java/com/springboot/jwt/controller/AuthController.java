package com.springboot.jwt.controller;

import java.net.URI;
import java.util.Collections;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.jwt.entity.ROLE_NAME;
import com.springboot.jwt.entity.Role;
import com.springboot.jwt.entity.User;
import com.springboot.jwt.exception.AppException;
import com.springboot.jwt.payload.ApiResponse;
import com.springboot.jwt.payload.JwtAuthenticationResponse;
import com.springboot.jwt.payload.LoginRequest;
import com.springboot.jwt.payload.SignUpRequest;
import com.springboot.jwt.repository.RoleRepository;
import com.springboot.jwt.repository.UserRepository;
import com.springboot.jwt.security.CustomUserDetailsService;
import com.springboot.jwt.security.JwtTokenProvider;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@PostMapping("/signin")
	public ResponseEntity<?> AuthenticateUser(@Valid @RequestBody LoginRequest loginRequest){
		logger.info("signin");
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOremail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwtToken = jwtTokenProvider.generateToken(authentication);
		
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwtToken));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> CreateUser(@Valid @RequestBody SignUpRequest signUpRequest){
		if(userRepository.existsByUsername(signUpRequest.getUsername())){
			return new ResponseEntity<>(new ApiResponse(false, "User name is already taken!"),
					HttpStatus.BAD_REQUEST);
		}
		if(userRepository.existsByEmail(signUpRequest.getEmail())){
			return new ResponseEntity<>(new ApiResponse(false, "Email address already use!"),
					HttpStatus.BAD_REQUEST);
		}
		
		Role userRole = roleRepository.findByRoleName(ROLE_NAME.ROLE_USER)
				.orElseThrow(() -> new AppException("User role not set!"));
		
		User user = new User(signUpRequest.getName(),signUpRequest.getUsername(),
				signUpRequest.getEmail(),signUpRequest.getPassword(),Collections.singleton(userRole));
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		
		User result = userRepository.save(user);
				
		URI location = ServletUriComponentsBuilder
				.fromCurrentContextPath().path("/api/users/{username}")
				.buildAndExpand(result.getName()).toUri();
		
		return ResponseEntity.created(location).body(new ApiResponse(true, "User register successfully"));
	}
	
	
}
