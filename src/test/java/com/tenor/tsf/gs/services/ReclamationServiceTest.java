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

import com.tenor.tsf.gs.entity.Reclamation;
import com.tenor.tsf.gs.entity.Salle;
import com.tenor.tsf.gs.entity.User;
import com.tenor.tsf.gs.enumeration.Statut;
import com.tenor.tsf.gs.exception.ReclamationNotFoundException;
import com.tenor.tsf.gs.exception.ReclamationNullException;
@RunWith(SpringRunner.class)
@SpringBootTest

public class ReclamationServiceTest {
@Autowired
ReclamationService ser;
	@Test
	public void testCreate() throws ReclamationNullException {
		Reclamation rec = new Reclamation();
		Reclamation rec1 = new Reclamation();
		User usr = new User();
		usr.setId(2l);
		Salle sl = new Salle();
		sl.setId(3l);
		
		LocalDateTime daterec = LocalDateTime.of(2019, 9, 22, 10, 33);
		rec.setMessage("tv is not working properly");
		rec.setDateRec(daterec);
		rec.setStatu(Statut.INPROGRESS);
		rec.setUser(usr);
		rec.setSalle(sl);
		rec=ser.create(rec);
		assertThat(ser.getById(rec.getId()).isPresent());

		LocalDateTime daterec1 = LocalDateTime.of(2019, 9, 15, 16, 55);
		rec1.setMessage("table is broken");
		rec1.setDateRec(daterec1);
		rec1.setStatu(Statut.NOtSEEN);
		rec1.setUser(usr);
		rec1.setSalle(sl);
		rec1=ser.create(rec1);
		assertThat(ser.getById(rec1.getId()).isPresent());
	}
	@Test(expected = ReclamationNullException.class )
	public void testCreate1() throws ReclamationNullException {
		Reclamation rec = new Reclamation();
		
		LocalDateTime daterec = LocalDateTime.of(2019, 9, 22, 10, 33);
		rec.setMessage("tv is not working properly");
		rec.setDateRec(daterec);
		rec.setStatu(Statut.INPROGRESS);
		ser.create(rec);

	}

	@Test
	public void testDelete() throws ReclamationNotFoundException {
		Long id =2l;
		ser.delete(id);
		assertEquals(Optional.empty(), ser.getById(id));
	}
	@Test(expected = ReclamationNotFoundException.class )
	public void testDelete1() throws ReclamationNotFoundException {
		Long id =5461l;
		ser.delete(id);
	}

	@Test
	public void testUpdate() throws ReclamationNotFoundException, ReclamationNullException {
		Reclamation rec = new Reclamation();
		User usr = new User();
		usr.setId(2l);
		Salle sl = new Salle();
		sl.setId(3l);
		
		LocalDateTime daterec = LocalDateTime.of(2019, 9, 22, 10, 33);
		rec.setId(11l);
		rec.setMessage("tv has been replaced");
		rec.setDateRec(daterec);
		rec.setStatu(Statut.DONE);
		rec.setUser(usr);
		rec.setSalle(sl);
		ser.update(rec);

	}
	@Test(expected = ReclamationNotFoundException.class)
	public void testUpdate1() throws ReclamationNotFoundException, ReclamationNullException {
		Reclamation rec = new Reclamation();
		User usr = new User();
		usr.setId(1l);
		Salle sl = new Salle();
		sl.setId(9l);
		
		LocalDateTime daterec = LocalDateTime.of(2019, 9, 22, 10, 33);
		rec.setId(77l);
		rec.setMessage("tv is now working properly and ready to use");
		rec.setDateRec(daterec);
		rec.setStatu(Statut.DONE);
		rec.setUser(usr);
		rec.setSalle(sl);
		ser.update(rec);
	}
	@Test(expected = ReclamationNullException.class)
	public void testUpdate11() throws ReclamationNotFoundException, ReclamationNullException {
		Reclamation rec = new Reclamation();
		
		LocalDateTime daterec = LocalDateTime.of(2019, 9, 22, 10, 33);
		rec.setId(77l);
		rec.setMessage("tv is now working properly and ready to use");
		rec.setDateRec(daterec);
		rec.setStatu(Statut.DONE);
		ser.update(rec);
	}

}
