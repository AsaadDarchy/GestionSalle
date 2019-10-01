package com.tenor.tsf.gs.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tenor.tsf.gs.entity.Reservation;
import com.tenor.tsf.gs.entity.Salle;
import com.tenor.tsf.gs.entity.User;
import com.tenor.tsf.gs.exception.ReservationAlreadyreservedException;
import com.tenor.tsf.gs.exception.ReservationDateException;
import com.tenor.tsf.gs.exception.ReservationNotFoundException;
import com.tenor.tsf.gs.exception.ReservationNullException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ReservationServiceTest {
	@Autowired
	ReservationService ser;

	@Test
	public void testCreate()
			throws ReservationNullException, ReservationDateException, ReservationAlreadyreservedException {
		Reservation res = new Reservation();
		Reservation res1 = new Reservation();
		User usr = new User();
		usr.setId(3l);
		Salle sl = new Salle();
		sl.setId(2l);

		LocalDateTime datedebut = LocalDateTime.of(2079, 10, 28, 10, 00);
		LocalDateTime datefin = LocalDateTime.of(2079, 10, 28, 12, 00);
		LocalDateTime datedebut1 = LocalDateTime.of(2059, 10, 29, 10, 00);
		LocalDateTime datefin1 = LocalDateTime.of(2059, 10, 29, 12, 00);

		res.setDateDebut(datedebut);
		res.setDateFin(datefin);
		res.setSalle(sl);
		res.setUser(usr);
		res = ser.create(res);
		assertThat(ser.getById(res.getId()).isPresent());

		res1.setDateDebut(datedebut1);
		res1.setDateFin(datefin1);
		res1.setSalle(sl);
		res1.setUser(usr);
		res1 = ser.create(res1);
		assertThat(ser.getById(res1.getId()).isPresent());

	}

	@Test(expected = ReservationDateException.class)
	public void testCreate1()
			throws ReservationNullException, ReservationDateException, ReservationAlreadyreservedException {
		Reservation res = new Reservation();
		User usr = new User();
		usr.setId(3l);
		Salle sl = new Salle();
		sl.setId(9l);

		LocalDateTime datedebut = LocalDateTime.of(2019, 9, 11, 10, 00);
		LocalDateTime datefin = LocalDateTime.of(2019, 9, 11, 12, 00);

		res.setDateDebut(datedebut);
		res.setDateFin(datefin);
		res.setSalle(sl);
		res.setUser(usr);
		ser.create(res);

	}

	@Test(expected = ReservationNullException.class)
	public void testCreate11()
			throws ReservationNullException, ReservationDateException, ReservationAlreadyreservedException {
		Reservation res = new Reservation();

		LocalDateTime datedebut = LocalDateTime.of(2019, 9, 11, 10, 00);
		LocalDateTime datefin = LocalDateTime.of(2019, 9, 11, 12, 00);

		res.setDateDebut(datedebut);
		res.setDateFin(datefin);
		ser.create(res);

	}

	@Test
	public void testDelete() throws ReservationNotFoundException {
		Long id = 13l;
		ser.delete(id);
		assertEquals(Optional.empty(), ser.getById(id));
	}

	@Test(expected = ReservationNotFoundException.class)
	public void testDelete1() throws ReservationNotFoundException {
		Long id = 2654l;
		ser.delete(id);
	}

	@Test
	public void testUpdate() throws ReservationNotFoundException, ReservationNullException, ReservationDateException {
		Reservation res = new Reservation();
		User usr = new User();
		usr.setId(3l);
		Salle sl = new Salle();
		sl.setId(1l);

		LocalDateTime datedebut = LocalDateTime.of(2019, 10, 30, 14, 00);
		LocalDateTime datefin = LocalDateTime.of(2019, 10, 30, 18, 00);

		res.setId(10l);
		res.setDateDebut(datedebut);
		res.setDateFin(datefin);
		res.setSalle(sl);
		res.setUser(usr);
		ser.update(res);

	}

	@Test(expected = ReservationNullException.class)
	public void testUpdate1() throws ReservationNotFoundException, ReservationNullException, ReservationDateException {
		Reservation res = new Reservation();

		LocalDateTime datedebut = LocalDateTime.of(2019, 9, 25, 14, 00);
		LocalDateTime datefin = LocalDateTime.of(2019, 9, 25, 18, 00);

		res.setId(2l);
		res.setDateDebut(datedebut);
		res.setDateFin(datefin);
		ser.update(res);

	}

	@Test(expected = ReservationNotFoundException.class)
	public void testUpdate11() throws ReservationNotFoundException, ReservationNullException, ReservationDateException {
		Reservation res = new Reservation();
		User usr = new User();
		usr.setId(3l);
		Salle sl = new Salle();
		sl.setId(9l);

		LocalDateTime datedebut = LocalDateTime.of(2019, 9, 25, 14, 00);
		LocalDateTime datefin = LocalDateTime.of(2019, 9, 25, 18, 00);

		res.setId(8l);
		res.setDateDebut(datedebut);
		res.setDateFin(datefin);
		res.setSalle(sl);
		res.setUser(usr);
		ser.update(res);

	}

	@Test(expected = ReservationDateException.class)
	public void testUpdate111()
			throws ReservationNotFoundException, ReservationNullException, ReservationDateException {
		Reservation res = new Reservation();
		User usr = new User();
		usr.setId(3l);
		Salle sl = new Salle();
		sl.setId(9l);

		LocalDateTime datedebut = LocalDateTime.of(2019, 9, 19, 14, 00);
		LocalDateTime datefin = LocalDateTime.of(2019, 9, 16, 18, 00);

		res.setId(2l);
		res.setDateDebut(datedebut);
		res.setDateFin(datefin);
		res.setSalle(sl);
		res.setUser(usr);
		ser.update(res);

	}

	@Test
	public void testCheckRoomAvialable() throws ReservationAlreadyreservedException {
		LocalDateTime datedebut = LocalDateTime.of(2029, 12, 28, 10, 00);
		LocalDateTime datefin = LocalDateTime.of(2029, 12, 28, 12, 00);
		ser.CheckRoomAvialable(9l, datedebut, datefin);

	}

	@Test(expected = ReservationAlreadyreservedException.class)
	public void testCheckRoomAvialable1() throws ReservationAlreadyreservedException {
		LocalDateTime datedebut = LocalDateTime.of(2029, 12, 28, 10, 00);
		LocalDateTime datefin = LocalDateTime.of(2029, 12, 28, 12, 00);
		ser.CheckRoomAvialable(9l, datedebut, datefin);

	}

}
