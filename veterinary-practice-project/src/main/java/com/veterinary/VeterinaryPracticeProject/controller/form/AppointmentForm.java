package com.veterinary.VeterinaryPracticeProject.controller.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;

import com.veterinary.VeterinaryPracticeProject.model.Appointment;
import com.veterinary.VeterinaryPracticeProject.model.Pet;
import com.veterinary.VeterinaryPracticeProject.model.Reason;
import com.veterinary.VeterinaryPracticeProject.repository.PetRepository;

public class AppointmentForm {
	
	@NotNull @NotEmpty
	private String petName;
	
	@NotNull
	private Reason reason;
	
	@NotNull
	private float paymentValue;
	
	public String getPetName() {
		return petName;
	}
	
	public void setPetName(String petName) {
		this.petName = petName;
	}
	
	public Reason getReason() {
		return reason;
	}
	
	public void setReason(Reason reason) {
		this.reason = reason;
	}
	
	public float getPaymentValue() {
		return paymentValue;
	}
	
	public void setPaymentValue(float paymentValue) {
		this.paymentValue = paymentValue;
	}

	public Appointment convert(PetRepository petRepository) {
		Pet pet = petRepository.findByName(petName);
		
		return new Appointment(pet, reason, paymentValue);
	}
}
