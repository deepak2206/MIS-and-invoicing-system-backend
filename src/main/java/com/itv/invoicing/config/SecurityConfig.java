package com.itv.invoicing.config;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.itv.invoicing.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig 
{
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtFilter jwtFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		return 
				http
				.cors()
				.and()
				.csrf().disable()
				.authorizeHttpRequests(request -> request
						.requestMatchers("/api/users/register", "/api/users/login")
						.permitAll()
						.anyRequest().authenticated())
//				.formLogin(Customizer.withDefaults())
				.httpBasic(Customizer.withDefaults()) // Enable Basic Auth (Prevents HTML response)
                .formLogin(form -> form.disable()) // Disable Form Login (Removes HTML login page)
//                .sessionManagement(session->
//                		session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))		
                
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		return provider;
		
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
	{
		
		return config.getAuthenticationManager();
	}
	
	

}
