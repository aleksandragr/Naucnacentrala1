package naucnaCentrala.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import naucnaCentrala.service.UserService;
import static naucnaCentrala.security.SecurityConstants.SIGN_UP_URL;
import static naucnaCentrala.security.SecurityConstants.LOGIN_URL;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//ovom metodom definisemo koju su resursi javni, a koji su osigurani 
	//SIGN_UP_URL smo postavili kao javno, a sve ostalo kao privatno
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		JWTAuthenticationFilter authenticationFilter = new JWTAuthenticationFilter(authenticationManager());
		authenticationFilter.setFilterProcessesUrl("/user/login");
		
		http.cors().and().csrf().disable().authorizeRequests()
		   .antMatchers(HttpMethod.POST, "/user/sign-up").permitAll()
		   //.antMatchers(HttpMethod.POST, "/user/login").permitAll()
		   .anyRequest().authenticated()
		   .and()
		   .addFilter(authenticationFilter)
		   .addFilter(new JWTAuthenticationFilter(authenticationManager()))
		   .addFilter(new JWTAuthorizationFilter(authenticationManager()))
		   
		   // this disables session creation on Spring Security
		   .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	        
		System.out.println("configure1");
		
	}
	
	
	//ova metoda nam omogucava da ukljucimo userService u security (metoda loadUserByUsername)
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
        System.out.println("configure2");
    }
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		System.out.println("configure3");
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
	    return source;
	    
	  }

}
