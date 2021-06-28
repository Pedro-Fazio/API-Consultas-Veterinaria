package com.veterinary.VeterinaryPracticeProject.repository;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.veterinary.VeterinaryPracticeProject.model.Pet;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PetRepositoryTest {
	
	@Autowired
	private PetRepository petRepository;

	@Test
	public void loadPetByHisName() {
		String petName = "fluffy";
		Pet pet = petRepository.findByName(petName);
		assertNotNull(pet);
		assertEquals(petName, pet.getName());
	}
	
	@Test
	public void loadNonexistentPetByHisName() {
		String petName = "itsnotapet";
		Pet pet = petRepository.findByName(petName);
		assertNull(pet);
	}
}
