package com.tenor.tsf.gs.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

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
import com.tenor.tsf.gs.entity.Reclamation;
import com.tenor.tsf.gs.entity.Salle;
import com.tenor.tsf.gs.entity.User;
import com.tenor.tsf.gs.enumeration.Statut;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReclamationControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	
	@Test
	public void testCreate() throws JsonProcessingException, Exception {
		Reclamation rec = new Reclamation();
		User usr = new User();
		usr.setId(2l);
		Salle sl = new Salle();
		sl.setId(3l);
		LocalDateTime daterec = LocalDateTime.of(2019, 9, 22, 10, 33);
		rec.setMessage("light switch is not working");
		rec.setDateRec(daterec);
		rec.setStatu(Statut.INPROGRESS);
		rec.setUser(usr);
		rec.setSalle(sl);
		
		ResultActions rs = mockMvc.perform( post("/Reclamation/create")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(rec)))
				.andExpect(status().isCreated());
		rs.andDo(print());
	}
	@Test
	public void testCreate1() throws JsonProcessingException, Exception {
		Reclamation rec = new Reclamation();
		ResultActions rs = mockMvc.perform( post("/Reclamation/create")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(rec)))
				.andExpect(status().isNotImplemented());
		rs.andDo(print());
	}
	
	
	@Test
	public void testGetAllReclamation() throws Exception {
		ResultActions rs = mockMvc.perform( get("/Reclamation"))
				.andExpect(status().isOk());
		rs.andDo(print());
	}

	@Test
	public void testDelete() throws Exception {
		ResultActions rs = mockMvc.perform( delete("/Reclamation/delete/{id}","6"))
				.andExpect(status().isOk());
		rs.andDo(print());
	}
	@Test
	public void testDelete1() throws Exception {
		ResultActions rs = mockMvc.perform( delete("/Reclamation/delete/{id}","68464686"))
				.andExpect(status().isNotFound());
		rs.andDo(print());
	}

	@Test
	public void testUpdate() throws JsonProcessingException, Exception {
		Reclamation rec = new Reclamation();
		User usr = new User();
		usr.setId(1l);
		Salle sl = new Salle();
		sl.setId(9l);
		LocalDateTime daterec = LocalDateTime.of(2019, 9, 22, 10, 33);
		rec.setId(6l);
		rec.setMessage("light switch is now working");
		rec.setDateRec(daterec);
		rec.setStatu(Statut.DONE);
		rec.setUser(usr);
		rec.setSalle(sl);
		
		ResultActions rs = mockMvc.perform( put("/Reclamation/update")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(rec)))
				.andExpect(status().isOk());
		rs.andDo(print());
	}
	@Test
	public void testUpdate1() throws JsonProcessingException, Exception {
		Reclamation rec = new Reclamation();
		User usr = new User();
		usr.setId(1l);
		Salle sl = new Salle();
		sl.setId(9l);
		LocalDateTime daterec = LocalDateTime.of(2019, 9, 22, 10, 33);
		rec.setId(665486l);
		rec.setMessage("light switch is now working");
		rec.setDateRec(daterec);
		rec.setStatu(Statut.DONE);
		rec.setUser(usr);
		rec.setSalle(sl);
		
		ResultActions rs = mockMvc.perform( put("/Reclamation/update")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(rec)))
				.andExpect(status().isNotFound());
		rs.andDo(print());
	}

}
