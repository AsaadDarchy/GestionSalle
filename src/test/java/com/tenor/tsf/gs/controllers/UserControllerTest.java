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
import com.tenor.tsf.gs.entity.Departement;
import com.tenor.tsf.gs.entity.User;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void testGetAllUser() throws Exception {
		ResultActions rs = mockMvc.perform( get("/User"))
				.andExpect(status().isOk());
		rs.andDo(print());
	}

	@Test
	public void testCreate() throws JsonProcessingException, Exception {
		Departement dept = new Departement();
		dept.setId(1l);
		User usr = new User();

		usr.setFirstName("bob");
		usr.setSecondName("JAVA");
		usr.setFunction("Designer");
		usr.setPseudo("BJ");
		usr.setPassword("5555");
		usr.setDepartement(dept);
		
		ResultActions rs = mockMvc.perform( post("/User/create")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(usr)))
				.andExpect(status().isCreated());
		rs.andDo(print());
	}
	@Test
	public void testCreate1() throws JsonProcessingException, Exception {
		User usr = new User();
		
		ResultActions rs = mockMvc.perform( post("/User/create")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(usr)))
				.andExpect(status().isNotImplemented());
		rs.andDo(print());
	}
	@Test
	public void testDelete() throws Exception {
		ResultActions rs = mockMvc.perform( delete("/User/delete/1"))
				.andExpect(status().isOk());
		rs.andDo(print());	}
	@Test
	public void testDelete1() throws Exception {
		ResultActions rs = mockMvc.perform( delete("/User/delete/89879648698"))
				.andExpect(status().isNotFound());
		rs.andDo(print());	}

	@Test
	public void testUpdate() throws JsonProcessingException, Exception {
		Departement dept = new Departement();
		dept.setId(1l);
		User usr = new User();

		usr.setFirstName("bob");
		usr.setId(2l);
		usr.setSecondName("desi");
		usr.setFunction("Designer");
		usr.setPseudo("BJ");
		usr.setPassword("5555");
		usr.setDepartement(dept);
		
		ResultActions rs = mockMvc.perform( put("/User/update")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(usr)))
				.andExpect(status().isOk());
		rs.andDo(print());
	}
	@Test
	public void testUpdate1() throws JsonProcessingException, Exception {
		Departement dept = new Departement();
		dept.setId(1l);
		User usr = new User();

		usr.setFirstName("bob");
		usr.setId(86846481l);
		usr.setSecondName("desi");
		usr.setFunction("Designer");
		usr.setPseudo("BJ");
		usr.setPassword("5555");
		usr.setDepartement(dept);
		
		ResultActions rs = mockMvc.perform( put("/User/update")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(usr)))
				.andExpect(status().isNotFound());
		rs.andDo(print());
	}

}
