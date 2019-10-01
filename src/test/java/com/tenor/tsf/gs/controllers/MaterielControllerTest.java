package com.tenor.tsf.gs.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenor.tsf.gs.entity.Materiel;
import com.tenor.tsf.gs.entity.Salle;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MaterielControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;


	@Test
	public void testCreate() throws JsonProcessingException, Exception {
		Materiel mat = new Materiel();
		Salle sal = new Salle();
		sal.setId(10l);
		mat.setCategortie("Electro");
		mat.setLibelle("air conditioner");
		mat.setSalle(sal);
		
		ResultActions rs = mockMvc.perform( post("/Materiel/create")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(mat)))
				.andExpect(status().isCreated());
		rs.andDo(print());
	}
	@Test
	public void testCreate1() throws JsonProcessingException, Exception {
		Materiel mat = new Materiel();
		
		ResultActions rs = mockMvc.perform( post("/Materiel/create")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(mat)))
				.andExpect(status().isNotImplemented());
		rs.andDo(print());
	}

	@Test
	public void testDelete() throws Exception {
		ResultActions rs = mockMvc.perform( delete("/Materiel/delete/{id}","19"))
				.andExpect(status().isOk());
		rs.andDo(print());
	}
	@Test
	public void testDelete1() throws Exception {
		ResultActions rs = mockMvc.perform( delete("/Materiel/delete/{id}","684684684"))
				.andExpect(status().isNotFound());
		rs.andDo(print());
	}

	@Test
	public void testUpdate() throws JsonProcessingException, Exception {
		Materiel mat = new Materiel();
		Salle sal = new Salle();
		sal.setId(10l);
		mat.setId(16l);
		mat.setCategortie("Electronique");
		mat.setLibelle("air conditioner");
		mat.setSalle(sal);
		
		ResultActions rs = mockMvc.perform( put("/Materiel/update")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(mat)))
				.andExpect(status().isOk());
		rs.andDo(print());
	}
	@Test
	public void testUpdate1() throws JsonProcessingException, Exception {
		Materiel mat = new Materiel();
		Salle sal = new Salle();
		sal.setId(10l);
		mat.setId(14545l);
		mat.setCategortie("Electronique");
		mat.setLibelle("air conditioner");
		mat.setSalle(sal);
		
		ResultActions rs = mockMvc.perform( put("/Materiel/update")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(mat)))
				.andExpect(status().isNotFound());
		rs.andDo(print());
	}
	@Test
	public void findAll() throws Exception {
		ResultActions rs = mockMvc.perform( get("/Materiel"))
				.andExpect(status().isOk());
		rs.andDo(print());
	}
	
}
