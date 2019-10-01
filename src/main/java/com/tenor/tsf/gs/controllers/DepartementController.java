package com.tenor.tsf.gs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenor.tsf.gs.entity.Departement;
import com.tenor.tsf.gs.exception.DepartementNullException;
import com.tenor.tsf.gs.exception.DepartmentNotFoundException;
import com.tenor.tsf.gs.services.DepartementService;

@RestController
@RequestMapping(value="/Departement")
public class DepartementController {
	@Autowired
	DepartementService ser;
	
	@GetMapping
	public ResponseEntity<List<Departement>>getAllDepartments(){
		if (ser.getAllDepartements().isEmpty()) {
			return new ResponseEntity<List<Departement>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Departement>>(ser.getAllDepartements(),HttpStatus.OK);
	}
	@PostMapping(value = "/create")
	public ResponseEntity<Departement> create(@RequestBody Departement dept) throws DepartementNullException {
		ser.create(dept);
		return new ResponseEntity<Departement>(HttpStatus.CREATED);

	}
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Departement> delete(@PathVariable Long id) throws  DepartmentNotFoundException {
		ser.deleteDept(id);
		return new ResponseEntity<Departement>(HttpStatus.OK);
	}
	@PutMapping(value = "/update")
	public ResponseEntity<Departement> update(@RequestBody Departement dept) throws DepartmentNotFoundException, DepartementNullException  {
		ser.update(dept);
		return new ResponseEntity<Departement>(HttpStatus.OK);
	} 
}
