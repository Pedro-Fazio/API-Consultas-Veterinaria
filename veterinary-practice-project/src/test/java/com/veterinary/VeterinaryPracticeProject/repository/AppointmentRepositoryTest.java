package com.veterinary.VeterinaryPracticeProject.repository;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.veterinary.VeterinaryPracticeProject.model.Appointment;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AppointmentRepositoryTest {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Test
	public void loadAppointmentsByPetName() {
		String petName = "fluffy";
		List<Appointment> appointment = appointmentRepository.findByPet_Name(petName);
		assertNotNull(appointment);
		
		for(int i = 0; i < appointment.size(); i++) {			
			assertEquals(petName, appointment.get(i).getPet().getName());
		}
	}
	
	@Test
	public void loadAppointmentsByNonexistentPetName() {
		String petName = "itsnotapet";
		List<Appointment> appointment = appointmentRepository.findByPet_Name(petName);
		assertTrue(appointment.isEmpty());
	}
}
