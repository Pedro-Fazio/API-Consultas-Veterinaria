package com.veterinary.VeterinaryPracticeProject.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.veterinary.VeterinaryPracticeProject.model.Admin;
import com.veterinary.VeterinaryPracticeProject.repository.AdminRepository;

@Service
public class AuthenticationService implements UserDetailsService {
	
	@Autowired
	private AdminRepository adminRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Admin> admin = adminRepository.findByEmail(username);
		
		if(admin.isPresent()) {
			return admin.get();
		}
		
		throw new UsernameNotFoundException("Invalid data! Please check your username and password");
	}
	
}
