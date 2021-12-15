package com.example.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.CoreMatchers.is;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CarroController.class)
class SpringCrudApplicationTests {
	@Autowired
    private MockMvc mvc;

	@Test
	@DisplayName("obtener todos los carros")
	public void getCarrosTest() throws Exception{
		mvc.perform(get("/getCarros").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(3)))
			.andExpect(jsonPath("$[0].nombre", is("picanto")))
			.andExpect(jsonPath("$[1].nombre", is("spark")))
			.andExpect(jsonPath("$[2].nombre", is("mazda 3")));
	}

	@Test
	@DisplayName("obtener el carro 1")
	public void getCarroTest() throws Exception{
		mvc.perform(get("/getCarro/{id}",1).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$.nombre", is("picanto")))
			.andExpect(jsonPath("$.marca", is("kia")));
	}

	@Test
	@DisplayName("guardar un carro")
	public void postCarroTest() throws Exception{
		Carro carro = new Carro(4L, "Pepito", "pepito");
		mvc.perform(post("/postCarro")
			.content(asJsonString(carro))
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(4)))
			.andExpect(jsonPath("$.nombre", is("Pepito")))
			.andExpect(jsonPath("$.marca", is("pepito")));

		mvc.perform(get("/getCarros").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(4)));
	}

	@Test
	@DisplayName("actualizar el carro 4")
	public void updateCarroTest() throws Exception{
		Carro carro = new Carro(3L, "pepito 2", "pepito 2");
		mvc.perform(put("/putCarro/{id}",4)
			.content(asJsonString(carro))
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(4)))
			.andExpect(jsonPath("$.nombre", is("pepito 2")))
			.andExpect(jsonPath("$.marca", is("pepito 2")));
	}

	@Test
	@DisplayName("eliminar carro 4")
	public void deleteCarroTest() throws Exception{
		mvc.perform(delete("/deleteCarro/{id}", 4)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(4)))
			.andExpect(jsonPath("$.nombre", is("pepito 2")))
			.andExpect(jsonPath("$.marca", is("pepito 2")));

		mvc.perform(get("/getCarros").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(3)));
	}


	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


}
