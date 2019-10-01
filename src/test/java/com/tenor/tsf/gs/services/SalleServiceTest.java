package com.tenor.tsf.gs.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tenor.tsf.gs.entity.Salle;
import com.tenor.tsf.gs.exception.SalleNotFoundException;
import com.tenor.tsf.gs.exception.SalleNullException;

@RunWith(SpringRunner.class)
@SpringBootTest

public class SalleServiceTest {
	@Autowired
	SalleService ser;

	@Test
	public void testCreate() throws SalleNullException {
		Salle sal = new Salle();
		Salle sal1 = new Salle();

		sal.setCapacite(25);
		sal.setLibelle("Rio");
		sal = ser.create(sal);
		assertThat(ser.getById(sal.getId()).isPresent());
		
		sal1.setCapacite(10);
		sal1.setLibelle("Berlin");
		sal1=ser.create(sal1);
		assertThat(ser.getById(sal1.getId()).isPresent());

	}
	@Test(expected = SalleNullException.class )
	public void testCreate1() throws SalleNullException {
		Salle sal = new Salle();

		ser.create(sal);


	}

	@Test
	public void testDelete() throws SalleNotFoundException {
		Long id = 22l;
		ser.delete(id);
		assertEquals(Optional.empty(), ser.getById(id));
	}
	@Test(expected = SalleNotFoundException.class)
	public void testDelete1() throws SalleNotFoundException {
		Long id = 24656482l;
		ser.delete(id);
	}


	@Test
	public void testUpdate() throws SalleNotFoundException, SalleNullException {
		Salle sal = new Salle();
		sal.setId(2l);
		sal.setCapacite(50);
		sal.setLibelle("Rio");
		ser.update(sal);
		assertEquals(sal, ser.getById(sal.getId()).get());

	}
	@Test(expected = SalleNullException.class)
	public void testUpdate1() throws SalleNotFoundException, SalleNullException {
		Salle sal = new Salle();
		ser.update(sal);
	}
	@Test(expected = SalleNotFoundException.class)
	public void testUpdate11() throws SalleNotFoundException, SalleNullException {
		Salle sal = new Salle();
		sal.setId(88l);
		sal.setCapacite(35);
		sal.setLibelle("Rio");
		ser.update(sal);
	}

	@Test
	public void testAllAvailableRooms() {
		LocalDateTime datedebut = LocalDateTime.of(2019, 9, 19, 10, 00);
		LocalDateTime datefin = LocalDateTime.of(2019, 9, 19, 12, 00); 
		ser.AllAvailableRooms(datedebut, datefin);
	}

}
