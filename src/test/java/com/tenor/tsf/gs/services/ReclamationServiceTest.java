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

import com.tenor.tsf.gs.entity.Departement;
import com.tenor.tsf.gs.entity.Reclamation;
import com.tenor.tsf.gs.entity.Salle;
import com.tenor.tsf.gs.entity.User;
import com.tenor.tsf.gs.enumeration.Statut;
import com.tenor.tsf.gs.exception.DepartementNullException;
import com.tenor.tsf.gs.exception.ReclamationNotFoundException;
import com.tenor.tsf.gs.exception.ReclamationNullException;
import com.tenor.tsf.gs.exception.SalleNullException;
import com.tenor.tsf.gs.exception.UserNullException;

@RunWith(SpringRunner.class)
@SpringBootTest

public class ReclamationServiceTest {
	@Autowired
	ReclamationService ser;
	@Autowired
	UserService usrSer;
	@Autowired
	SalleService salleSer;
	@Autowired
	DepartementService deptSer;

	@Test
	public void testCreate()
			throws ReclamationNullException, DepartementNullException, UserNullException, SalleNullException {
		Reclamation rec = new Reclamation();
		Reclamation rec1 = new Reclamation();

		Departement dept = new Departement();
		dept.setLibelle("DEV");
		deptSer.create(dept);

		User usr = new User();
		usr.setFirstName("yassine");
		usr.setSecondName("makassi");
		usr.setFunction("Designer");
		usr.setPseudo("YM");
		usr.setPassword("1234");
		usr.setDepartement(dept);
		usr = usrSer.create(usr);

		Salle sl = new Salle();
		sl.setLibelle("Munich");
		sl.setCapacite(200);
		salleSer.create(sl);

		LocalDateTime daterec = LocalDateTime.of(2019, 9, 22, 10, 33);
		rec.setMessage("tv is not working properly");
		rec.setDateRec(daterec);
		rec.setStatu(Statut.INPROGRESS);
		rec.setUser(usr);
		rec.setSalle(sl);
		rec = ser.create(rec);
		assertThat(ser.getById(rec.getId()).isPresent());

		LocalDateTime daterec1 = LocalDateTime.of(2019, 9, 15, 16, 55);
		rec1.setMessage("table is broken");
		rec1.setDateRec(daterec1);
		rec1.setStatu(Statut.NOtSEEN);
		rec1.setUser(usr);
		rec1.setSalle(sl);
		rec1 = ser.create(rec1);
		assertThat(ser.getById(rec1.getId()).isPresent());
	}

	@Test(expected = ReclamationNullException.class)
	public void testCreate1() throws ReclamationNullException {
		Reclamation rec = new Reclamation();

		LocalDateTime daterec = LocalDateTime.of(2019, 9, 22, 10, 33);
		rec.setMessage("tv is not working properly");
		rec.setDateRec(daterec);
		rec.setStatu(Statut.INPROGRESS);
		ser.create(rec);

	}

	@Test
	public void testDelete() throws ReclamationNotFoundException, DepartementNullException, UserNullException,
			SalleNullException, ReclamationNullException {
		Departement dept = new Departement();
		dept.setLibelle("DEV");
		deptSer.create(dept);

		User usr = new User();
		usr.setFirstName("yassine");
		usr.setSecondName("makassi");
		usr.setFunction("Designer");
		usr.setPseudo("YM");
		usr.setPassword("1234");
		usr.setDepartement(dept);
		usr = usrSer.create(usr);

		Salle sl = new Salle();
		sl.setLibelle("Munich");
		sl.setCapacite(200);
		salleSer.create(sl);

		Reclamation rec = new Reclamation();
		LocalDateTime daterec = LocalDateTime.of(2019, 9, 22, 10, 33);
		rec.setMessage("tv is not working properly");
		rec.setDateRec(daterec);
		rec.setStatu(Statut.INPROGRESS);
		rec.setUser(usr);
		rec.setSalle(sl);
		rec = ser.create(rec);

		Long id = rec.getId();
		ser.delete(id);
		assertEquals(Optional.empty(), ser.getById(id));
	}

	@Test(expected = ReclamationNotFoundException.class)
	public void testDelete1() throws ReclamationNotFoundException {
		Long id = 5461l;
		ser.delete(id);
	}

	@Test
	public void testUpdate() throws ReclamationNotFoundException, ReclamationNullException, DepartementNullException,
			UserNullException, SalleNullException {
		Departement dept = new Departement();
		dept.setLibelle("DEV");
		deptSer.create(dept);

		User usr = new User();
		usr.setFirstName("yassine");
		usr.setSecondName("makassi");
		usr.setFunction("Designer");
		usr.setPseudo("YM");
		usr.setPassword("1234");
		usr.setDepartement(dept);
		usr = usrSer.create(usr);

		Salle sl = new Salle();
		sl.setLibelle("Munich");
		sl.setCapacite(200);
		salleSer.create(sl);

		Reclamation rec1 = new Reclamation();
		LocalDateTime daterec1 = LocalDateTime.of(2019, 9, 22, 10, 33);
		rec1.setMessage("tv is not working properly");
		rec1.setDateRec(daterec1);
		rec1.setStatu(Statut.INPROGRESS);
		rec1.setUser(usr);
		rec1.setSalle(sl);
		ser.create(rec1);

		Reclamation rec = new Reclamation();
		LocalDateTime daterec = LocalDateTime.of(2019, 9, 22, 10, 33);
		rec.setId(rec1.getId());
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
