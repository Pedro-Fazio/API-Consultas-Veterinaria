package com.veterinary.VeterinaryPracticeProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veterinary.VeterinaryPracticeProject.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	List<Appointment> findByPet_Name(String petName);	
}
