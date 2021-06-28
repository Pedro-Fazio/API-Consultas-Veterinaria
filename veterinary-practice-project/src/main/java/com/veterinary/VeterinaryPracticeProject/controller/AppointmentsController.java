package com.veterinary.VeterinaryPracticeProject.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.veterinary.VeterinaryPracticeProject.controller.dto.AppointmentDto;
import com.veterinary.VeterinaryPracticeProject.controller.form.AppointmentForm;
import com.veterinary.VeterinaryPracticeProject.controller.form.UpdateAppointmentForm;
import com.veterinary.VeterinaryPracticeProject.model.Appointment;
import com.veterinary.VeterinaryPracticeProject.repository.AppointmentRepository;
import com.veterinary.VeterinaryPracticeProject.repository.PetRepository;

@RestController
@RequestMapping("/appointments")
public class AppointmentsController {
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private PetRepository petRepository;
	
	@GetMapping
	public List<AppointmentDto> getAppointments(String petName) {
		if(petName == null) {
			List<Appointment> appointments = appointmentRepository.findAll();
			return AppointmentDto.convert(appointments);
		} else {
			List<Appointment> appointments = appointmentRepository.findByPet_Name(petName);
			return AppointmentDto.convert(appointments);
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<AppointmentDto> postAppointment(@RequestBody @Valid AppointmentForm appointmentForm, UriComponentsBuilder uriBuilder) {
		Appointment appointment = appointmentForm.convert(petRepository);
		appointmentRepository.save(appointment);
		
		URI uri = uriBuilder.path("/appointments/{id}").buildAndExpand(appointment.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new AppointmentDto(appointment));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AppointmentDto> filter(@PathVariable Long id) {
		Optional<Appointment> appointment = appointmentRepository.findById(id);
		
		if(appointment.isPresent()) {
			return ResponseEntity.ok(new AppointmentDto(appointment.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<AppointmentDto> putAppointment(@PathVariable Long id, @RequestBody @Valid UpdateAppointmentForm updateAppointmentForm) {
		Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
		
		if(optionalAppointment.isPresent()) {			
			Appointment appointment = updateAppointmentForm.update(id, appointmentRepository);	
			return ResponseEntity.ok(new AppointmentDto(appointment));
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteAppointment(@PathVariable Long id) {
		Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
		
		if(optionalAppointment.isPresent()) {	
			appointmentRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}

