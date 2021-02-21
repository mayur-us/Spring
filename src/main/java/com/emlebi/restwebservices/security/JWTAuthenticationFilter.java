package com.emlebi.restwebservices.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.emlebi.restwebservices.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * Authentication Filer - User to authenticate the user credentials.
 * On successful authentication, it returns a JWT Token.
 * 
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		// We set the default login URL, so we will not need to define a login endpoint
		// in our controller explicitly
		setFilterProcessesUrl("/api/v1/users/login");
	}

	/*
	 * The attemptAuthentication function runs when the user tries to log in to our
	 * application. It reads the credentials, creates a user POJO from them, and
	 * then checks the credentials to authenticate.
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {
			User credentials = new ObjectMapper().readValue(req.getInputStream(), User.class);

			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(),
					credentials.getPassword(), new ArrayList<>())// We are not using roles, so presently we are keeping
																	// roles as Empty
			);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * If the authentication is successful, the successfulAuthentication method runs
	 * and returns a JWTToken to the user
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException {

		String userName = ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername();
		String token = JWT.create()
//	                .withSubject(((User) auth.getPrincipal()).getUsername())
				.withSubject(userName)
				.withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));

		String body = userName + " " + token;

		res.getWriter().write(body);
		res.getWriter().flush();
	}

}
