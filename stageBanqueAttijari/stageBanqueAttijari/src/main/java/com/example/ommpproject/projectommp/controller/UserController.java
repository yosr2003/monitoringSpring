package com.example.ommpproject.projectommp.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ommpproject.projectommp.JWT.JwtUtils;
import com.example.ommpproject.projectommp.models.ERole;
import com.example.ommpproject.projectommp.models.JwtResponse;
import com.example.ommpproject.projectommp.models.LoginRequest;
import com.example.ommpproject.projectommp.models.MessageResponse;
import com.example.ommpproject.projectommp.models.Role;
import com.example.ommpproject.projectommp.models.SignupRequest;
import com.example.ommpproject.projectommp.models.User;
import com.example.ommpproject.projectommp.models.UserDetailsImpl;
import com.example.ommpproject.projectommp.repositories.RoleRepository;
import com.example.ommpproject.projectommp.repositories.UserRepositiry;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class UserController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepositiry userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
	    Authentication authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    System.out.println("authentication -------- " + authentication);
	    String jwt = jwtUtils.generateJwtToken(authentication);

	    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
	    List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
	            .collect(Collectors.toList());
	    boolean firstLogin = userDetails.isFirstLogin();
	    System.out.println("userDetails ------- " + userDetails.toString());

	    return ResponseEntity.ok(
	            new JwtResponse(jwt, userDetails.getIdUser(), userDetails.getFirstName(), userDetails.getEmail(), roles, firstLogin));
	}


	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		

		// Create new user's account
		User user = new User(signUpRequest.getFirstName(),  signUpRequest.getLastName(),  signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}
		
		
		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}