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

import com.tenor.tsf.gs.entity.User;
import com.tenor.tsf.gs.exception.UserNotFoundException;
import com.tenor.tsf.gs.exception.UserNullException;
import com.tenor.tsf.gs.services.UserService;

@RestController
@RequestMapping(value="/User")
public class UserController {
	@Autowired
	UserService ser;
	
	@GetMapping
	public ResponseEntity<List<User>>getAllUser(){
		if (ser.getAllUsers().isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<User>>(ser.getAllUsers(),HttpStatus.OK);
	}
	@PostMapping(value = "/create")
	public ResponseEntity<User> create(@RequestBody User us) throws UserNullException {
		ser.create(us);
		return new ResponseEntity<User>(HttpStatus.CREATED);

	}
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<User> delete(@PathVariable Long id) throws UserNotFoundException     {
		ser.delete(id);
		return new ResponseEntity<User>(HttpStatus.OK);
	}
	@PutMapping(value = "/update")
	public ResponseEntity<User> update(@RequestBody User us) throws UserNotFoundException, UserNullException  {
		ser.update(us);
		return new ResponseEntity<User>(HttpStatus.OK);
	} 
}
