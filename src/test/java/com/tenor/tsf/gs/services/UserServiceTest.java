package com.tenor.tsf.gs.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tenor.tsf.gs.entity.Departement;
import com.tenor.tsf.gs.entity.User;
import com.tenor.tsf.gs.exception.DepartementNullException;
import com.tenor.tsf.gs.exception.UserNotFoundException;
import com.tenor.tsf.gs.exception.UserNullException;

@RunWith(SpringRunner.class)
@SpringBootTest

public class UserServiceTest {
	@Autowired
	UserService ser;

	@Test
	public void testCreate() throws DepartementNullException, UserNullException {
		Departement dept = new Departement();
		dept.setId(2l);

		User usr = new User();
		User usr1 = new User();
		User usr2 = new User();

		usr.setFirstName("yassine");
		usr.setSecondName("makassi");
		usr.setFunction("Designer");
		usr.setPseudo("YM");
		usr.setPassword("1234");
		usr.setDepartement(dept);
		usr = ser.create(usr);
		assertThat(ser.getById(usr.getId()).isPresent());

		usr1.setFirstName("amine");
		usr1.setSecondName("masri");
		usr1.setFunction("Designer");
		usr1.setPseudo("AM");
		usr1.setPassword("0000");
		usr1.setDepartement(dept);
		usr1 = ser.create(usr1);
		assertThat(ser.getById(usr1.getId()).isPresent());

		usr2.setFirstName("karim");
		usr2.setSecondName("omari");
		usr2.setFunction("Designer");
		usr2.setPseudo("KO");
		usr2.setPassword("9999");
		usr2.setDepartement(dept);
		usr2 = ser.create(usr2);
		assertThat(ser.getById(usr1.getId()).isPresent());
	}

	@Test(expected = UserNullException.class)
	public void testCreate1() throws UserNullException {
		User usr = new User();
		ser.create(usr);
	}

	@Test
	public void testDelete() throws UserNotFoundException {

		long id = 4l;
		ser.delete(id);
		assertEquals(Optional.empty(), ser.getById(id));

	}

	@Test(expected = UserNotFoundException.class)
	public void testDelete1() throws UserNotFoundException {
		long id = 45555l;
		ser.delete(id);
	}

	@Test
	public void testUpdate() throws UserNotFoundException, UserNullException {
		Departement dept = new Departement();
		dept.setId(2l);
		User usr = new User();
		usr.setId(2l);
		usr.setFirstName("yassine");
		usr.setSecondName("makarib");
		usr.setFunction("Designer");
		usr.setPseudo("YM");
		usr.setPassword("1234");
		usr.setDepartement(dept);
		ser.update(usr);

	}

	@Test(expected = UserNotFoundException.class)
	public void testUpdate1() throws UserNotFoundException, UserNullException {
		Departement dept = new Departement();
		dept.setId(2l);
		User usr = new User();
		usr.setId(55l);
		usr.setFirstName("yassine");
		usr.setSecondName("makarib");
		usr.setFunction("Designer");
		usr.setPseudo("YM");
		usr.setPassword("1234");
		usr.setDepartement(dept);
		ser.update(usr);

	}

	@Test(expected = UserNullException.class)
	public void testUpdate11() throws UserNotFoundException, UserNullException {
		User usr = new User();
		ser.update(usr);

	}

}
