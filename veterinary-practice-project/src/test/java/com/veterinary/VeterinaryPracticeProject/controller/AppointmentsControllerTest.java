package com.veterinary.VeterinaryPracticeProject.controller;

import java.net.URI;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AppointmentsControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void returnOkAndResponseBodyWhenGettingDataFromAppointments() throws Exception {
		URI uri = new URI("/appointments");
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.get(uri))
		.andExpect(MockMvcResultMatchers
				.status().is(200))
		.andReturn();
	}
	
	@Test
	public void returnForbiddenWhenGettingDataFromAppointmentsWithInvalidUri() throws Exception {
		URI uri = new URI("/notvaliduri");
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.get(uri))
		.andExpect(MockMvcResultMatchers
				.status().isForbidden());
	}
	
	@Test
	public void returnOkAndResponseBodyWhenGettingSpecificDataFromAppointments() throws Exception {
		URI uri = new URI("/appointments/4");
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.get(uri))
		.andExpect(MockMvcResultMatchers
				.status().isOk())
		.andReturn();
	}
	
	@Test
	public void returnNotFoundWhenGettingSpecificDataFromAppointmentsWithInvalidId() throws Exception {
		URI uri = new URI("/appointments/10000");
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.get(uri))
		.andExpect(MockMvcResultMatchers
				.status().isNotFound());
	}
	
	@Test
	public void returnCreatedAndResponseBodyWhenPostingAJsonDataAppointmentWithValidToken() throws Exception {
		String token;
		String bearerToken = "Bearer ";
		URI uri = new URI("/auth");
		String json = "{\"email\": \"pedro@email.com\", \"pwd\": \"123456\"}";
		
		MvcResult authResult = mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.isOk())
		.andReturn();
		
		JSONObject obj = new JSONObject(authResult.getResponse().getContentAsString());
		token = obj.getString("token");
		bearerToken += token;
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders
				.post("/appointments").header("Authorization", bearerToken).contentType(MediaType.APPLICATION_JSON)
	            .content("{\"petName\":\"fluffy\",\"reason\":\"GROOMING\",\"paymentValue\":\"200\"}"))
	            .andDo(MockMvcResultHandlers.print())
	            .andExpect(MockMvcResultMatchers 
	            		.status().isCreated())
	            .andReturn();

		String content = result.getResponse().getContentAsString();
	}
	
	@Test
	public void returnForbiddenWhenPostingAJsonDataAppointmentWithInvalidToken() throws Exception {
		String token = "123";
		String bearerToken = "Bearer" + token;
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders
				.post("/appointments").header("Authorization", bearerToken).contentType(MediaType.APPLICATION_JSON)
	            .content("{\"petName\":\"fluffy\",\"reason\":\"GROOMING\",\"paymentValue\":\"200\"}"))
	            .andDo(MockMvcResultHandlers.print())
	            .andExpect(MockMvcResultMatchers
	            		.status().isForbidden())
	            .andReturn();

		String content = result.getResponse().getContentAsString();
	}
	
	@Test
	public void returnBadRequestWhenPostingAJsonDataAppointmentWithValidTokenAndEmptyAttribute() throws Exception {
		String token;
		String bearerToken = "Bearer ";
		URI uri = new URI("/auth");
		String json = "{\"email\": \"pedro@email.com\", \"pwd\": \"123456\"}";
		
		MvcResult authResult = mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.isOk())
		.andReturn();
		
		JSONObject obj = new JSONObject(authResult.getResponse().getContentAsString());
		token = obj.getString("token");
		bearerToken += token;
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/appointments").header("Authorization", bearerToken).contentType(MediaType.APPLICATION_JSON)
	            .content("{\"petName\":\"\",\"reason\":\"\",\"paymentValue\":\"\"}"))
	            .andDo(MockMvcResultHandlers.print())
	            .andExpect(MockMvcResultMatchers
	            		.status().isBadRequest());
	}
	
	@Test
	public void returnOkWhenDeletingByIdWithAValidToken() throws Exception {
		String token;
		String bearerToken = "Bearer ";
		URI uri = new URI("/auth");
		String json = "{\"email\": \"pedro@email.com\", \"pwd\": \"123456\"}";
		
		MvcResult authResult = mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.isOk())
		.andReturn();
		
		JSONObject obj = new JSONObject(authResult.getResponse().getContentAsString());
		token = obj.getString("token");
		bearerToken += token;
		
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/appointments/25").header("Authorization", bearerToken))
	            .andDo(MockMvcResultHandlers.print())
	            .andExpect(MockMvcResultMatchers 
	            		.status().isOk());
	}
	
	@Test
	public void returnNotFoundWhenDeletingByInvalidIdWithAValidToken() throws Exception {
		String token;
		String bearerToken = "Bearer ";
		URI uri = new URI("/auth");
		String json = "{\"email\": \"pedro@email.com\", \"pwd\": \"123456\"}";
		
		MvcResult authResult = mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.isOk())
		.andReturn();
		
		JSONObject obj = new JSONObject(authResult.getResponse().getContentAsString());
		token = obj.getString("token");
		bearerToken += token;
		
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/appointments/1000").header("Authorization", bearerToken))
	            .andDo(MockMvcResultHandlers.print())
	            .andExpect(MockMvcResultMatchers 
	            		.status().isNotFound());
	}
	
	@Test
	public void returnForbiddenWhenDeletingByIdWithAInvalidToken() throws Exception {
		String token = "123";
		String bearerToken = "Bearer" + token;
		
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/appointments/4").header("Authorization", bearerToken))
	            .andDo(MockMvcResultHandlers.print())
	            .andExpect(MockMvcResultMatchers 
	            		.status().isForbidden());
	}
}
