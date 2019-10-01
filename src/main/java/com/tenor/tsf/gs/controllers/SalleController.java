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

import com.tenor.tsf.gs.entity.Salle;
import com.tenor.tsf.gs.exception.SalleNotFoundException;
import com.tenor.tsf.gs.exception.SalleNullException;
import com.tenor.tsf.gs.services.SalleService;

@RestController
@RequestMapping(value="/Salle")
public class SalleController {
	@Autowired
	SalleService ser;
	
	@GetMapping(value = "/getAllSalle")
	public ResponseEntity<List<Salle>>getAllSalle(){
		if (ser.geAllRooms().isEmpty()) {
			return new ResponseEntity<List<Salle>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Salle>>(ser.geAllRooms(),HttpStatus.OK);
	}
	@PostMapping(value = "/create")
	public ResponseEntity<Salle> create(@RequestBody Salle sal) throws SalleNullException {
		ser.create(sal);
		return new ResponseEntity<Salle>(HttpStatus.CREATED);

	}
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Salle> delete(@PathVariable Long id) throws SalleNotFoundException     {
		ser.delete(id);
		return new ResponseEntity<Salle>(HttpStatus.OK);
	}
	@PutMapping(value = "/update")
	public ResponseEntity<Salle> update(@RequestBody Salle sal) throws  SalleNullException, SalleNotFoundException  {
		ser.update(sal);
		return new ResponseEntity<Salle>(HttpStatus.OK);
	} 
}
