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
import com.tenor.tsf.gs.entity.Salle;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SalleControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testGetAllSalle() throws Exception {
		ResultActions rs = mockMvc.perform( get("/Salle/getAllSalle"))
				.andExpect(status().isOk());
		rs.andDo(print());
	}

	@Test
	public void testCreate() throws JsonProcessingException, Exception {
		Salle sal = new Salle();

		sal.setCapacite(100);
		
		sal.setLibelle("Tokyo");
		ResultActions rs = mockMvc.perform(post("/Salle/create").contentType("application/json")
				.content(objectMapper.writeValueAsString(sal))).andExpect(status().isCreated());
		rs.andDo(print());
	}
	@Test
	public void testCreate1() throws JsonProcessingException, Exception {
		Salle sal = new Salle();

		ResultActions rs = mockMvc.perform(post("/Salle/create").contentType("application/json")
				.content(objectMapper.writeValueAsString(sal))).andExpect(status().isBadRequest());
		rs.andDo(print());
	}

	@Test
	public void testDelete() throws Exception {
		ResultActions rs = mockMvc.perform( delete("/Salle/delete/11"))
				.andExpect(status().isOk());
		rs.andDo(print());	}
	@Test
	public void testDelete1() throws Exception {
		ResultActions rs = mockMvc.perform( delete("/Salle/delete/684684611"))
				.andExpect(status().isNotFound());
		rs.andDo(print());	}


	@Test
	public void testUpdate() throws JsonProcessingException, Exception {
		Salle sal = new Salle();

		sal.setCapacite(55);
		sal.setId(2l);
		sal.setLibelle("Rio");
		ResultActions rs = mockMvc.perform(put("/Salle/update").contentType("application/json")
				.content(objectMapper.writeValueAsString(sal))).andExpect(status().isOk());
		rs.andDo(print());
	}
	@Test
	public void testUpdate1() throws JsonProcessingException, Exception {
		Salle sal = new Salle();

		sal.setCapacite(55);
		sal.setId(684684l);
		sal.setLibelle("CASA");
		ResultActions rs = mockMvc.perform(put("/Salle/update").contentType("application/json")
				.content(objectMapper.writeValueAsString(sal))).andExpect(status().isNotFound());
		rs.andDo(print());
	}

}
