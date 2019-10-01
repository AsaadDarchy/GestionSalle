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
import com.tenor.tsf.gs.entity.Reservation;
import com.tenor.tsf.gs.entity.Salle;
import com.tenor.tsf.gs.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReservationControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testGetAllReservation() throws Exception {
		ResultActions rs = mockMvc.perform( get("/Reservation"))
				.andExpect(status().isOk());
		rs.andDo(print());
	}

	@Test
	public void testCreate() throws JsonProcessingException, Exception {
		Reservation res = new Reservation();
		User usr = new User();
		usr.setId(3l);
		Salle sl = new Salle();
		sl.setId(2l);

		LocalDateTime datedebut = LocalDateTime.of(2019, 11, 19, 8, 00);
		LocalDateTime datefin = LocalDateTime.of(2019, 11, 19, 12, 00);

		res.setDateDebut(datedebut);
		res.setDateFin(datefin);
		res.setSalle(sl);
		res.setUser(usr);
		ResultActions rs = mockMvc.perform(post("/Reservation/create").contentType("application/json")
				.content(objectMapper.writeValueAsString(res))).andExpect(status().isCreated());
		rs.andDo(print());
	}

	@Test
	public void testCreate1() throws JsonProcessingException, Exception {
		Reservation res = new Reservation();
		ResultActions rs = mockMvc.perform(post("/Reservation/create").contentType("application/json")
				.content(objectMapper.writeValueAsString(res))).andExpect(status().isNotImplemented());
		rs.andDo(print());
	}
	@Test
	public void testCreate11() throws JsonProcessingException, Exception {
		Reservation res = new Reservation();
		User usr = new User();
		usr.setId(3l);
		Salle sl = new Salle();
		sl.setId(1l);

		LocalDateTime datedebut = LocalDateTime.of(2019, 11, 15, 10, 00);
		LocalDateTime datefin = LocalDateTime.of(2019, 11, 15, 16, 00);

		res.setDateDebut(datedebut);
		res.setDateFin(datefin);
		res.setSalle(sl);
		res.setUser(usr);
		ResultActions rs = mockMvc.perform(post("/Reservation/create").contentType("application/json")
				.content(objectMapper.writeValueAsString(res))).andExpect(status().isBadRequest());
		rs.andDo(print());
	}


	@Test
	public void testDelete() throws Exception {
		ResultActions rs = mockMvc.perform( delete("/Reservation/delete/7"))
				.andExpect(status().isOk());
		rs.andDo(print());
	}

	@Test
	public void testUpdate() throws JsonProcessingException, Exception {
		Reservation res = new Reservation();
		User usr = new User();
		usr.setId(3l);
		Salle sl = new Salle();
		sl.setId(1l);

		LocalDateTime datedebut = LocalDateTime.of(2019, 10, 15, 10, 00);
		LocalDateTime datefin = LocalDateTime.of(2019, 10, 15, 16, 00);
		res.setId(10l);
		res.setDateDebut(datedebut);
		res.setDateFin(datefin);
		res.setSalle(sl);
		res.setUser(usr);
		ResultActions rs = mockMvc.perform(put("/Reservation/update").contentType("application/json")
				.content(objectMapper.writeValueAsString(res))).andExpect(status().isOk());
		rs.andDo(print());
	}
	@Test
	public void testUpdate1() throws JsonProcessingException, Exception {
		Reservation res = new Reservation();
		User usr = new User();
		usr.setId(3l);
		Salle sl = new Salle();
		sl.setId(9l);

		LocalDateTime datedebut = LocalDateTime.of(2019, 11, 15, 10, 00);
		LocalDateTime datefin = LocalDateTime.of(2019, 11, 15, 16, 00);
		res.setId(65117l);
		res.setDateDebut(datedebut);
		res.setDateFin(datefin);
		res.setSalle(sl);
		res.setUser(usr);
		ResultActions rs = mockMvc.perform(put("/Reservation/update").contentType("application/json")
				.content(objectMapper.writeValueAsString(res))).andExpect(status().isNotFound());
		rs.andDo(print());
	}

}
