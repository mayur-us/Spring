package com.emlebi.restwebservices.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.emlebi.restwebservices.model.User;
import com.emlebi.restwebservices.repositories.UserRepository;

/*
 * Service class for the 'User' Entity object
 * Does the fronting/redirection of calls from the Controller to the Respository.
 * Main objective is achieve loose coupling between Controller and Persistance layer
 */

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user != null) {
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

//	public String login(String username, String password) {
//		try {
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//			String token = JWT.create()
//	                .withSubject(((User) auth.getPrincipal()).getUsername())
//	                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
//	                .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));
//
//	        String body = ((User) auth.getPrincipal()).getUsername() + " " + token;
//
//	        res.getWriter().write(body);
//	        res.getWriter().flush();
//			
//			
//			return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
//		} catch (AuthenticationException e) {
//			throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
//		}
//	}

}
