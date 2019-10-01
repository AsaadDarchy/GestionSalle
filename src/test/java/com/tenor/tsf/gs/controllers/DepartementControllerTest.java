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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenor.tsf.gs.entity.Departement;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class DepartementControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testGetAllDepartments() throws Exception {
		ResultActions rs = mockMvc.perform(get("/Departement")).andExpect(status().isOk());
		rs.andDo(print());
	}

	@Test
	public void testCreate() throws JsonProcessingException, Exception {
		Departement dept = new Departement();
		dept.setLibelle("Security");

		ResultActions rs = mockMvc.perform(post("/Departement/create").contentType("application/json")
				.content(objectMapper.writeValueAsString(dept))).andExpect(status().isCreated());
		rs.andDo(print());
	}

	@Test
	public void testCreate1() throws JsonProcessingException, Exception {
		Departement dept = new Departement();

		ResultActions rs = mockMvc.perform(post("/Departement/create").contentType("application/json")
				.content(objectMapper.writeValueAsString(dept))).andExpect(status().isNotImplemented());
		rs.andDo(print());
	}

	@Test
	public void testDelete() throws Exception {
		ResultActions rs = mockMvc.perform( delete("/Departement/delete/15"))
				.andExpect(status().isOk());
		rs.andDo(print());
	}
	@Test
	public void testDelete1() throws Exception {
		ResultActions rs = mockMvc.perform( delete("/Departement/delete/649648"))
				.andExpect(status().isNotFound());
		rs.andDo(print());}

	@Test
	public void testUpdate() throws JsonProcessingException, Exception {
		Departement dept = new Departement();
		dept.setId(13l);
		dept.setLibelle("Security & maintenance");
		ResultActions rs = mockMvc.perform(put("/Departement/update").contentType("application/json")
				.content(objectMapper.writeValueAsString(dept))).andExpect(status().isOk());
		rs.andDo(print());
	}
	@Test
	public void testUpdate1() throws JsonProcessingException, Exception {
		Departement dept = new Departement();
		dept.setId(16464646l);
		dept.setLibelle("Security & maintenance");
		ResultActions rs = mockMvc.perform(put("/Departement/update").contentType("application/json")
				.content(objectMapper.writeValueAsString(dept))).andExpect(status().isNotFound());
		rs.andDo(print());
	}

}
