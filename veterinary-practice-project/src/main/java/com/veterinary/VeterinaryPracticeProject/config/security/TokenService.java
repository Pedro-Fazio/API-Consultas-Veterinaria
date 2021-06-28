package com.veterinary.VeterinaryPracticeProject.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.veterinary.VeterinaryPracticeProject.model.Admin;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${veterinarypracticeproject.jwt.expiration}")
	private String expiration;
	
	@Value("${veterinarypracticeproject.jwt.secret}")
	private String secret;

	public String generateToken(Authentication authentication) {
		Admin adminLogged = (Admin) authentication.getPrincipal();
		Date today = new Date();
		Date expirationDate = new Date(today.getTime() + Long.parseLong(expiration));
		
		
		return Jwts.builder()
				.setIssuer("Pedro's Veterinary")
				.setSubject(adminLogged.getId().toString())
				.setIssuedAt(today)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean isValidToken(String token) {
		
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;			
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdAdmin(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		
		return Long.parseLong(claims.getSubject());
	}
	
}
