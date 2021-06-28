package com.veterinary.VeterinaryPracticeProject.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Pet {    
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	@ManyToOne
    private PetOwner petOwner;
    private String name;
    private Date petBirthday;
    private String breed;
    
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public PetOwner getPetOwner() {
		return petOwner;
	}
	
	public void setId(PetOwner petOwner) {
		this.petOwner = petOwner;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getPetBirthday() {
		return petBirthday;
	}
	
	public void setPetBirthday(Date petBirthday) {
		this.petBirthday = petBirthday;
	}
	
	public String getBreed() {
		return breed;
	}
	
	public void setBreed(String breed) {
		this.breed = breed;
	}
    
    
}
