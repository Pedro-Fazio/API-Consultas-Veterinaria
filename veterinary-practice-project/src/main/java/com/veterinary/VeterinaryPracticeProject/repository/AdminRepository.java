package com.veterinary.VeterinaryPracticeProject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veterinary.VeterinaryPracticeProject.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
	
	Optional<Admin> findByEmail(String email);
}
