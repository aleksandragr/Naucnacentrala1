package naucnaCentrala.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import naucnaCentrala.model.User;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static naucnaCentrala.security.SecurityConstants.EXPIRATION_TIME;
import static naucnaCentrala.security.SecurityConstants.SECRET;
import static naucnaCentrala.security.SecurityConstants.HEADER_STRING;
import static naucnaCentrala.security.SecurityConstants.TOKEN_PREFIX;

//ova klasa nam je odgovorna za autentifikaciju
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
	
	//ovde parsiramo korisnikove kredencijale i predajemo ih authentication manager-u
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) 
			throws AuthenticationException{
		System.out.println("attempt");
		try {
			
			User creds = new ObjectMapper().readValue(req.getInputStream(), User.class);
			
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
						creds.getUsername(), creds.getPassword(), new ArrayList<>())
					);
			
				
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		
	}
	
	
	//metoda koja se koristi kad je korisnik uspesno ulogovan. Mi koristimo ovu metodu da napravimo JWT
	@Override
	protected void successfulAuthentication(HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain,
            Authentication auth) throws IOException, ServletException {
		
		System.out.println("successful");
		String token = JWT.create().withSubject(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
						.sign(HMAC512(SECRET.getBytes()));
		
		
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
		
	}
	
		
}
