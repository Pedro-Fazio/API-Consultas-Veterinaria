package com.veterinary.VeterinaryPracticeProject.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.veterinary.VeterinaryPracticeProject.model.Appointment;
import com.veterinary.VeterinaryPracticeProject.model.Pet;
import com.veterinary.VeterinaryPracticeProject.model.Reason;
import com.veterinary.VeterinaryPracticeProject.repository.AppointmentRepository;

public class UpdateAppointmentForm {
	@NotNull @NotEmpty
	private String petName;
	
	@NotNull
	private Reason reason;
	
	@NotNull
	private float paymentValue;
	
	@NotNull
	private boolean isPaid;

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

	public boolean getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public Appointment update(Long id, AppointmentRepository appointmentRepository) {
		Appointment appointment = appointmentRepository.getById(id);
		
		Pet pet = appointment.getPet();
		pet.setName(this.petName);
		
		appointment.setPet(pet);
		appointment.setReason(this.reason);
		appointment.setPaymentValue(this.paymentValue);
		appointment.setIsPaid(this.isPaid);
		
		return appointment;
	}
	
	
}
