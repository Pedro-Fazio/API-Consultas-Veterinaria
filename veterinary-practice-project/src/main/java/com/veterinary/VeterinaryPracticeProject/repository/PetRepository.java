package com.veterinary.VeterinaryPracticeProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veterinary.VeterinaryPracticeProject.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {

	Pet findByName(String petName);
}
