package practica.spring_security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import practica.spring_security.security.filters.JwtAuthenticationFilter;
import practica.spring_security.security.filters.JwtValidationFilter;

@Configuration
public class SpringSecurityConfig {
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception {
		
		AuthenticationManager manager = authenticationConfiguration.getAuthenticationManager(); 
		
		JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(manager);
		
		JwtValidationFilter jwtValidationFilter = new JwtValidationFilter(manager);
		
		return httpSecurity
				.authorizeHttpRequests( (authorize)-> authorize
						.requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
						.requestMatchers(HttpMethod.POST, "/api/users/{id}/role").hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST, "/api/courses").hasRole("TEACHER")
						.anyRequest().authenticated())
						.addFilter(jwtAuthenticationFilter)
						.addFilter(jwtValidationFilter)
						.csrf(AbstractHttpConfigurer::disable)
						.sessionManagement( manegement -> manegement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
						.build();
	}
}
