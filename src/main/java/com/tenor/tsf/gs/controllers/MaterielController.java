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

import com.tenor.tsf.gs.entity.Materiel;
import com.tenor.tsf.gs.exception.MaterielNotFoundException;
import com.tenor.tsf.gs.exception.MaterielNullException;
import com.tenor.tsf.gs.services.MaterielService;

@RestController
@RequestMapping(value="/Materiel")
public class MaterielController {
	@Autowired
	MaterielService ser;
	
	@GetMapping
	public ResponseEntity<List<Materiel>>getAllMateriel(){
		if (((List<Materiel>) ser.getAllMateriels()).isEmpty()) {
			return new ResponseEntity<List<Materiel>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Materiel>>(ser.getAllMateriels(),HttpStatus.OK);
	}
	@PostMapping(value="/create")
	public ResponseEntity<Materiel> create(@RequestBody Materiel mat) throws MaterielNullException {
		ser.create(mat);
		return new ResponseEntity<Materiel>(HttpStatus.CREATED);

	}
	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity<Materiel> delete(@PathVariable Long id) throws MaterielNotFoundException   {
		ser.delete(id);
		return new ResponseEntity<Materiel>(HttpStatus.OK);
	}
	@PutMapping(value="/update")
	public ResponseEntity<Materiel> update(@RequestBody Materiel mat) throws MaterielNullException, MaterielNotFoundException {
		ser.update(mat);
		return new ResponseEntity<Materiel>(HttpStatus.OK);
	} 
}
