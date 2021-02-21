package com.emlebi.restwebservices.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.emlebi.restwebservices.service.JwtUserDetailsService;


/*
 * Configuration class to apply Security on URL patterns, which endpoints are subject to the Authetication and AUthorization filters
 * and also which endpoints are fully permitted without authentication and authorization
 * Also the JWTAuthenticationFilter and JWTAuthorizationFilter which contain the AUthetication and Authorization Logic 
 * are applied/wired and activated in this class
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
//	@Bean 
//	public BCryptPasswordEncoder bCryptPasswordEncoder1() {
//	    return new BCryptPasswordEncoder(); 
//	}

    public WebSecurityConfig(JwtUserDetailsService jwtUserDetailsService) {
        this.jwtUserDetailsService = jwtUserDetailsService;
    }
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
    	
    	// configure AuthenticationManager so that it knows from where to load
 		// user for matching credentials
    	auth.userDetailsService(jwtUserDetailsService).passwordEncoder(bCryptPasswordEncoder);// Use BCryptPasswordEncoder for password encoding
    }
    
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//    	
//
//        // Disable CSRF (cross site request forgery)
//        http.csrf().disable();
//
//        // No session will be created or used by spring security
////        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        
////        http.cors().and().authorizeRequests()
//        		// dont authenticate this particular request
////                .antMatchers(SecurityConstants.SIGN_UP_URL).permitAll()
//        http.csrf().ignoringAntMatchers("/h2-console");
//        
//        http.authorizeRequests()
//        .antMatchers(
//                HttpMethod.GET,
//                "/",
//                "/csrf",
//                "/service-status/v1/task/status",
//                "/swagger-ui.html",
//                "/*.html",
//                "/*.js",
//                "/favicon.ico",
//                "/**/*.html",
//                "/**/*.css",
//                "/**/*.png",
//                "/webjars/**",
//                "/configuration/**",
//                "/v2/**",
//                "/swagger-resources/**",
//                "/**/*.js"
//        ).permitAll()
//        .antMatchers("/h2-console/**")
//        .permitAll()
//        		.antMatchers("/*").permitAll()
//                .antMatchers("/users/saveUser").permitAll()
//                // all other requests need to be authenticated
//                .anyRequest().authenticated()
//                .and()
//                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
//                .addFilter(new JWTAuthorizationFilter(authenticationManager()));
//                
//    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().
                authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/users/saveUser")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/users/login")
                .permitAll()
//                .antMatchers(HttpMethod.GET, "/api/v1/person/getAllPersons")
//                .permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/person/getPerson/**")
                .permitAll()
                .antMatchers(HttpMethod.POST,"/api/v1/person/createPerson")
                .permitAll()         
                .antMatchers(HttpMethod.DELETE,"/api/v1/person/deletePerson/**")
                .permitAll() 
                .antMatchers(HttpMethod.PUT,"/api/v1/person/updatePerson/**")
                .permitAll() 
                .antMatchers("/h2-console/**")
                .permitAll()
                .antMatchers(
                      HttpMethod.GET,
                      "/",
                      "/csrf",
                      "/service-status/v1/task/status",
                      "/swagger-ui.html",
                      "/*.html",
                      "/*.js",
                      "/favicon.ico",
                      "/**/*.html",
                      "/**/*.css",
                      "/**/*.png",
                      "/webjars/**",
                      "/configuration/**",
                      "/v2/**",
                      "/swagger-resources/**",
                      "/**/*.js")
                .permitAll()
                .anyRequest().authenticated()
      .and()
      .addFilter(new JWTAuthenticationFilter(authenticationManager()))
      .addFilter(new JWTAuthorizationFilter(authenticationManager()));
//                .anyRequest().authenticated().and()
//                .addFilter( new AuthenticationFilter(authenticationManager()) )
//                //.addFilter(getJWTAuthenticationFilter()) // To create a custom URL for authenticaiton filter
//                .addFilter( new AuthorizationFilter( authenticationManager() ))
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        http.headers().frameOptions().disable();
    }
}
