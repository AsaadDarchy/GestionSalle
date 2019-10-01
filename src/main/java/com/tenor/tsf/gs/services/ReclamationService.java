package com.tenor.tsf.gs.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenor.tsf.gs.entity.Reclamation;
import com.tenor.tsf.gs.exception.ReclamationNotFoundException;
import com.tenor.tsf.gs.exception.ReclamationNullException;
import com.tenor.tsf.gs.repositories.ReclamationRepo;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@Transactional
public class ReclamationService {
	@Autowired
	ReclamationRepo repo;

	public Reclamation create(Reclamation Reclamation) throws ReclamationNullException {
		if (Reclamation.getMessage() == null || Reclamation.getDateRec() == null || Reclamation.getSalle() == null
				|| Reclamation.getStatu() == null || Reclamation.getUser() == null
				|| Reclamation.getMessage() == null && Reclamation.getDateRec() == null
						&& Reclamation.getSalle() == null && Reclamation.getStatu() == null
						&& Reclamation.getUser() == null) {
			throw new ReclamationNullException();
		} else {
			repo.save(Reclamation);
			log.info("the created Reclamation is :" + Reclamation);

		}

		return Reclamation;

	}

	public Optional<Reclamation> getById(Long id) {
		return repo.findById(id);

	}

	public void delete(Long id) throws ReclamationNotFoundException {
		if (getById(id).isPresent()) {
			log.info("the removed reclamation is :" + getById(id));
			log.info("number of recmlamations :" + repo.count());
			repo.deleteById(id);

		} else {
			throw new ReclamationNotFoundException();
		}
	}

	public void update(Reclamation rec) throws ReclamationNotFoundException, ReclamationNullException {
		Validate.notNull(rec, "object can't be null");
		Boolean trouve = false;
		if (rec.getMessage() == null || rec.getDateRec() == null || rec.getId() == null || rec.getSalle() == null
				|| rec.getStatu() == null || rec.getUser() == null
				|| rec.getMessage() == null && rec.getDateRec() == null && rec.getId() == null && rec.getSalle() == null
						&& rec.getStatu() == null && rec.getUser() == null) {
			throw new ReclamationNullException();
		} else {
			if (getById(rec.getId()).isPresent()) {
				repo.save(rec);
				trouve = true;
			}

		}
		if (trouve == false) {
			throw new ReclamationNotFoundException();
		}
	}

	public List<Reclamation> getAllReclamation() {
		return (List<Reclamation>) repo.findAll();
	}

}
