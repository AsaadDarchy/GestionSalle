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

import com.tenor.tsf.gs.entity.Reservation;
import com.tenor.tsf.gs.exception.ReservationAlreadyreservedException;
import com.tenor.tsf.gs.exception.ReservationDateException;
import com.tenor.tsf.gs.exception.ReservationNotFoundException;
import com.tenor.tsf.gs.exception.ReservationNullException;
import com.tenor.tsf.gs.services.ReservationService;

@RestController
@RequestMapping(value="/Reservation")
public class ReservationController {
	@Autowired
	ReservationService ser;
	
	@GetMapping
	public ResponseEntity<List<Reservation>>getAllReservation(){
		if (ser.getAllReservation().isEmpty()) {
			return new ResponseEntity<List<Reservation>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Reservation>>(ser.getAllReservation(),HttpStatus.OK);
	}
	@PostMapping(value = "/create")
	public ResponseEntity<Reservation> create(@RequestBody Reservation dept) throws ReservationNullException, ReservationDateException, ReservationAlreadyreservedException{
		ser.create(dept);
		return new ResponseEntity<Reservation>(HttpStatus.CREATED);

	}
	@DeleteMapping(value = "delete/{id}")
	public ResponseEntity<Reservation> delete(@PathVariable Long id) throws ReservationNotFoundException{
		ser.delete(id);
		return new ResponseEntity<Reservation>(HttpStatus.OK);
	}
	@PutMapping(value = "/update")
	public ResponseEntity<Reservation> update(@RequestBody Reservation dept) throws ReservationNotFoundException, ReservationNullException, ReservationDateException{
		ser.update(dept);
		return new ResponseEntity<Reservation>(HttpStatus.OK);
	} 
}
