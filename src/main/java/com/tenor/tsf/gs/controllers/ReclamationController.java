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

import com.tenor.tsf.gs.entity.Reclamation;
import com.tenor.tsf.gs.exception.ReclamationNotFoundException;
import com.tenor.tsf.gs.exception.ReclamationNullException;
import com.tenor.tsf.gs.services.ReclamationService;

@RestController
@RequestMapping(value="/Reclamation")
public class ReclamationController {
	@Autowired
	ReclamationService ser;
	
	@GetMapping
	public ResponseEntity<List<Reclamation>>getAllReclamation(){
		if (ser.getAllReclamation().isEmpty()) {
			return new ResponseEntity<List<Reclamation>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Reclamation>>(ser.getAllReclamation(),HttpStatus.OK);
	}
	@PostMapping(value = "/create")
	public ResponseEntity<Reclamation> create(@RequestBody Reclamation rec) throws ReclamationNullException {
		ser.create(rec);
		return new ResponseEntity<Reclamation>(HttpStatus.CREATED);

	}
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Reclamation> delete(@PathVariable Long id) throws ReclamationNotFoundException  {
		ser.delete(id);
		return new ResponseEntity<Reclamation>(HttpStatus.OK);
	}
	@PutMapping(value = "/update")
	public ResponseEntity<Reclamation> update(@RequestBody Reclamation rec) throws ReclamationNotFoundException, ReclamationNullException {
		ser.update(rec);
		return new ResponseEntity<Reclamation>(HttpStatus.OK);
	} 
}
