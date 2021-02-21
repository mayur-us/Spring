package com.emlebi.restwebservices.controllers;

//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.emlebi.restwebservices.model.User;
import com.emlebi.restwebservices.repositories.UserRepository;
import com.emlebi.restwebservices.service.JwtUserDetailsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/*
 * Controller class for User. Front end class for accessing
 * REST endpoints for the entity 'User'
 * Rest endpoints for following functionalities are available
 * 1)Save a User
 * The login functionality is taken care in JWTAuthenticationFilter hence we do not need to define explicit endpoint
 * for Login in this controller.
 * 
 */

@RestController
@RequestMapping("/api/v1/users")
@Api(tags = "users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@ApiOperation(value = "Create User")
	@PostMapping
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public User saveUser(@RequestBody final User user) {
		user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
		return userRepository.saveAndFlush(user);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
