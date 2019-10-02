package com.tenor.tsf.gs.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tenor.tsf.gs.entity.Departement;
import com.tenor.tsf.gs.exception.DepartementNullException;
import com.tenor.tsf.gs.exception.DepartmentNotFoundException;
@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartementServiceTest {
@Autowired
DepartementService ser;
	@Test
	public void testCreate() throws DepartementNullException {
		Departement dep= new Departement();
		dep.setLibelle("Security");
		dep = ser.create(dep);
		assertThat(ser.getById(dep.getId()).isPresent());

		
	}
	@Test(expected = DepartementNullException.class )
	public void testCreate1() throws DepartementNullException {
		Departement dep= new Departement();
		Departement dep1= new Departement();
		dep1=ser.create(dep);
		assertThat(ser.getById(dep1.getId()).isPresent());
		
	}

	@Test
	public void testGetById() throws DepartementNullException {
		Departement dep= new Departement();
		dep.setLibelle("Security");
		ser.create(dep);
		Long id =dep.getId();
		ser.getById(id);
	}


	@Test
	public void testDeleteDept() throws DepartmentNotFoundException, DepartementNullException {
		Departement dep= new Departement();
		dep.setLibelle("Security");
		ser.create(dep);
		Long id =dep.getId();
		ser.deleteDept(id);
		assertEquals(Optional.empty(), ser.getById(id));
	}
	@Test(expected =DepartmentNotFoundException.class )
	public void testDeleteDept1() throws DepartmentNotFoundException {
		Long id =634684l;
		ser.deleteDept(id);
	}

	@Test
	public void testUpdate() throws DepartmentNotFoundException, DepartementNullException {
		Departement dep= new Departement();
		Departement dep1= new Departement();
		dep1.setLibelle("Cleaning");
		ser.create(dep1);
		dep.setId(dep1.getId());
		dep.setLibelle("Cleaning 2");
		ser.update(dep);
	}
	@Test(expected = DepartmentNotFoundException.class)
	public void testUpdate1() throws DepartmentNotFoundException, DepartementNullException {
		Departement dep= new Departement();
		dep.setId(64569486l);
		dep.setLibelle("Cleaning");
		ser.update(dep);
	}
	@Test(expected = DepartementNullException.class)
	public void testUpdate11() throws DepartmentNotFoundException, DepartementNullException {
		Departement dep= new Departement();
		ser.update(dep);
	}

	@Test
	public void testGetAllDepartements() {
ser.getAllDepartements();
	}

}
