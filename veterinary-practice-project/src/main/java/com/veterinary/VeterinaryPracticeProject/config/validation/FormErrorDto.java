package com.veterinary.VeterinaryPracticeProject.config.validation;

public class FormErrorDto {
	private String variable;
	private String errorMessage;
	
	public FormErrorDto(String variable, String errorMessage) {
		super();
		this.variable = variable;
		this.errorMessage = errorMessage;
	}

	public String getVariable() {
		return variable;
	}

	public String getError() {
		return errorMessage;
	}
}
