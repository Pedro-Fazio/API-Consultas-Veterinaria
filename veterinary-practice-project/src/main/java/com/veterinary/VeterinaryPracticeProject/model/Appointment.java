package com.veterinary.VeterinaryPracticeProject.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Appointment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	private Pet pet;
	@Enumerated(EnumType.STRING)
	private Reason reason;
	@Enumerated(EnumType.STRING)
	private PaymentType paymentType = PaymentType.CASH;
	private float paymentValue;
	private boolean isPaid = false;
	
	public Appointment() {
	}
	
	public Appointment(Pet pet, Reason reason, float paymentValue) {
		this.pet = pet;
		this.reason = reason;
		this.paymentValue = paymentValue;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Pet getPet() {
		return pet;
	}
	
	public void setPet(Pet pet) {
		this.pet = pet;
	}
	
	public float getPaymentValue() {
		return paymentValue;
	}

	public void setPaymentValue(float paymentValue) {
		this.paymentValue = paymentValue;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}
	
	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
	
	public Reason getReason() {
		return reason;
	}
	
	public void setReason(Reason reason) {
		this.reason = reason;
	}

	public boolean getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}
}