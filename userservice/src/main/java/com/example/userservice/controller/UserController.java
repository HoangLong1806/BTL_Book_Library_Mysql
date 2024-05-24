package com.example.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userservice.data.User;
import com.example.userservice.model.UserDTO;
import com.example.userservice.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

//	@Autowired
//	private IUserService userService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/listUser")
	@Cacheable(cacheNames = "cache1", key = "'listUser'")
	public List<User> getAllUser() {
		return userService.getAllUser();
	}
	
	@PostMapping("/addUser")
	public User addUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@PostMapping("/login")
	public UserDTO login(@RequestBody UserDTO dto) {
		userService.saveUser(UserDTO.toEntity(dto));
		return userService.login(dto.getUsername(), dto.getPassword());
	}
	
	
	

}
