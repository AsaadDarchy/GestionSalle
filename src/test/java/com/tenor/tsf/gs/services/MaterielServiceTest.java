package com.tenor.tsf.gs.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tenor.tsf.gs.entity.Materiel;
import com.tenor.tsf.gs.entity.Salle;
import com.tenor.tsf.gs.exception.MaterielNotFoundException;
import com.tenor.tsf.gs.exception.MaterielNullException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MaterielServiceTest {
	@Autowired
	MaterielService ser;

	@Autowired
	SalleService sa;

	@Test
	public void testCreate() throws MaterielNullException {
		Materiel mat = new Materiel();
		Materiel mat1 = new Materiel();
		Materiel mat2 = new Materiel();
		Salle sal = new Salle();
		sal.setId(2l);

		mat.setLibelle("TV");
		mat.setCategortie("Electronics");
		mat.setSalle(sal);
		mat = ser.create(mat);
		assertThat(ser.getById(mat.getId()).isPresent());

		mat1.setLibelle("Computer");
		mat1.setCategortie("Electronics");
		mat1.setSalle(sal);
		mat1 = ser.create(mat1);
		assertThat(ser.getById(mat1.getId()).isPresent());

		mat2.setLibelle("Projector");
		mat2.setCategortie("Electronics");
		mat2.setSalle(sal);
		mat2 = ser.create(mat2);
		assertThat(ser.getById(mat2.getId()).isPresent());

	}

	@Test(expected = MaterielNullException.class)
	public void testCreate1() throws MaterielNullException {
		Materiel mat = new Materiel();
		Salle sal = new Salle();

		mat.setLibelle("CARPET");
		mat.setSalle(sal);
		ser.create(mat);

	}

	@Test
	public void testDelete() throws MaterielNotFoundException {
		Long id = 4l;
		ser.delete(id);
		assertEquals(Optional.empty(), ser.getById(id));

	}

	@Test(expected = MaterielNotFoundException.class)
	public void testDelete1() throws MaterielNotFoundException {
		Long id = 16486l;
		ser.delete(id);
	}

	@Test
	public void testUpdate() throws MaterielNullException, MaterielNotFoundException {
		Materiel mat = new Materiel();
		Salle sal = new Salle();
		sal.setId(1l);
		mat.setId(1l);
		mat.setLibelle("Chair");
		mat.setCategortie("FURNITURE");
		mat.setSalle(sal);
		ser.update(mat);

	}

	@Test(expected = MaterielNullException.class)
	public void testUpdate1() throws MaterielNullException, MaterielNotFoundException {
		Materiel mat = new Materiel();
		mat.setId(5l);
		mat.setLibelle("TV");
		ser.update(mat);

	}

	@Test(expected = MaterielNotFoundException.class)
	public void testUpdate11() throws MaterielNullException, MaterielNotFoundException {
		Materiel mat = new Materiel();
		Salle sal = new Salle();
		sal.setId(9l);
		mat.setId(99l);
		mat.setLibelle("TV");
		mat.setCategortie("ELECTRONIQUES");
		mat.setSalle(sal);
		ser.update(mat);

	}

}
