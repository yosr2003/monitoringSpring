package com.example.ommpproject.projectommp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.ommpproject.projectommp.models.User;
import com.example.ommpproject.projectommp.repositories.UserRepositiry;
import com.example.ommpproject.projectommp.services.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/users")

public class EmpController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserRepositiry userRepository;

	
	
//	@PostMapping
//	public User addPort(@RequestBody User user) {
//
//		return userService.addUser(user);
//	}

//	 @GetMapping("/list/{id}")
//	  public ResponseEntity<List<User>> getAllPortsByid(@PathVariable(value = "id") Long id) {
//	    if (!userRepository.existsById(id)) {
//	      throw new MessageResponse("Not found Tutorial with id = " );
//	    }
//	    List<User> users = userRepository.findByUserId(id);
//	    return new ResponseEntity<>(users, HttpStatus.OK);
//	  }
	@GetMapping 
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	
	@GetMapping ("/mail/{email}")
	public User getUserByMail(@PathVariable String email ) {
//		 User user=userRepository.findByEmail(email).get();
		 Optional<User> user=userRepository.findByEmail(email);
		 return user.isPresent() ? user.get(): null;
	}
	
	
	

	@GetMapping("/{id}")
	public User getUser(@PathVariable Long id) {
		return userService.getUserById(id);
	}

	@PutMapping("/{id}")
	public User update( @RequestBody User user) {
		return userService.editUser(user);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		userService.deleteUserById(id);
	}

}
