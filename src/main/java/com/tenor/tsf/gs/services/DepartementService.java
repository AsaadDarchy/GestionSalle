package com.tenor.tsf.gs.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenor.tsf.gs.entity.Departement;
import com.tenor.tsf.gs.exception.DepartementNullException;
import com.tenor.tsf.gs.exception.DepartmentNotFoundException;
import com.tenor.tsf.gs.repositories.DepartementRepo;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@Transactional
public class DepartementService {
	@Autowired
	DepartementRepo repo;

	public Departement create(Departement departement) throws DepartementNullException {
		if (departement.getLibelle() == null) {
			throw new DepartementNullException();
		} else {
			Departement depto = repo.save(departement);
			log.info("the created departement is :" + depto);

		}

		return departement;

	}

	public Optional<Departement> getById(Long id) {
		return repo.findById(id);

	}

	public void deleteDept(Long id) throws DepartmentNotFoundException {
		if (getById(id).isPresent()) {
			repo.deleteById(id);
			log.info("the removed depatement is :" + getById(id));
			log.info("number of depatements :" + repo.count());
		} else {
			throw new DepartmentNotFoundException();
		}

	}

	public void update(Departement dept) throws DepartmentNotFoundException, DepartementNullException {
		Boolean trouve = false;
		if (dept.getLibelle() == null || dept.getId() == null || dept.getLibelle() == null && dept.getId() == null) {
			throw new DepartementNullException();
		} else if (getById(dept.getId()).isPresent()) {
			repo.save(dept);
			trouve = true;
		}
		if (trouve == false) {
			throw new DepartmentNotFoundException();
		}
	}

	public List<Departement> getAllDepartements() {
		return (List<Departement>) repo.findAll();

	}
}