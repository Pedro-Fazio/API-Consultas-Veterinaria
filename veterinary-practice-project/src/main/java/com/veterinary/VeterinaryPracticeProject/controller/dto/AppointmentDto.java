package com.veterinary.VeterinaryPracticeProject.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.veterinary.VeterinaryPracticeProject.model.Appointment;
import com.veterinary.VeterinaryPracticeProject.model.PaymentType;
import com.veterinary.VeterinaryPracticeProject.model.Reason;

public class AppointmentDto {
	
	private Long id;
	private String petName;
	private Reason reason;
	private PaymentType paymentType;
	private float paymentValue;
	private boolean isPaid;
	
	public AppointmentDto(Appointment appointment) {
		this.id = appointment.getId();
		this.petName = appointment.getPet().getName();
		this.reason = appointment.getReason();
		this.paymentType = appointment.getPaymentType();
		this.paymentValue = appointment.getPaymentValue();
		this.isPaid = appointment.getIsPaid();
	}
	
	public boolean getIsPaid() {
		return isPaid;
	}

	public Long getId() {
		return id;
	}
	
	public String getPetName() {
		return petName;
	}
	
	public Reason getReason() {
		return reason;
	}
	
	public PaymentType getPaymentType() {
		return paymentType;
	}
	
	public float getPaymentValue() {
		return paymentValue;
	}

	public static List<AppointmentDto> convert(List<Appointment> appointments) {		
		return appointments.stream().map(AppointmentDto::new).collect(Collectors.toList());
	}
}
