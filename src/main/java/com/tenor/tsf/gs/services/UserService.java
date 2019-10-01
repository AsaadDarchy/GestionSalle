package com.tenor.tsf.gs.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenor.tsf.gs.entity.User;
import com.tenor.tsf.gs.exception.UserNotFoundException;
import com.tenor.tsf.gs.exception.UserNullException;
import com.tenor.tsf.gs.repositories.UserRepo;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@Transactional
public class UserService {

	@Autowired
	UserRepo repo;

	public User create(User user) throws UserNullException {
		if (user.getFirstName() == null || user.getDepartement() == null || user.getFunction() == null
				|| user.getPassword() == null || user.getPseudo() == null || user.getSecondName() == null
				|| user.getFirstName() == null && user.getDepartement() == null && user.getFunction() == null
						&& user.getPassword() == null && user.getPseudo() == null && user.getSecondName() == null) {
			throw new UserNullException();
		} else {
			repo.save(user);
		}
		log.info("create");
		log.info(user);
		return user;
	}

	public Optional<User> getById(Long id) {
		return repo.findById(id);

	}

	public void delete(Long id) throws UserNotFoundException {
		if (getById(id).isPresent()) {
			repo.deleteById(id);
			log.info("the department has been removed with success");
			log.info("number of departments left is  :" + repo.count());
		} else {
			throw new UserNotFoundException();
		}
	}

	public void update(User us) throws UserNotFoundException, UserNullException {
		Boolean trouve = false;
		if (us.getFirstName() == null || us.getDepartement() == null || us.getFunction() == null || us.getId() == null
				|| us.getPassword() == null || us.getPseudo() == null || us.getSecondName() == null
				|| us.getFirstName() == null && us.getDepartement() == null && us.getFunction() == null
						&& us.getId() == null && us.getPassword() == null && us.getPseudo() == null
						&& us.getSecondName() == null) {
			throw new UserNullException();
		} else if (getById(us.getId()).isPresent()) {
			repo.save(us);
			trouve = true;

		}
		if (trouve == false) {
			throw new UserNotFoundException();
		}

	}

	public List<User> getAllUsers() {
		return (List<User>) repo.findAll();
	}
}
