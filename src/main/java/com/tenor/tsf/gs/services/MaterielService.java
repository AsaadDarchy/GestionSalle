package com.tenor.tsf.gs.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenor.tsf.gs.entity.Materiel;
import com.tenor.tsf.gs.entity.Salle;
import com.tenor.tsf.gs.exception.MaterielNotFoundException;
import com.tenor.tsf.gs.exception.MaterielNullException;
import com.tenor.tsf.gs.repositories.MaterielRepo;
import com.tenor.tsf.gs.repositories.SalleRepo;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@Transactional
public class MaterielService {
	@Autowired
	MaterielRepo repo;
	@Autowired
	SalleRepo slrepo;

	public Materiel create(Materiel Materiel) throws MaterielNullException {
		if (Materiel.getLibelle() == null || Materiel.getCategortie() == null
				|| Materiel.getLibelle() == null && Materiel.getCategortie() == null) {
			throw new MaterielNullException();
		} else {
			for (Salle sl : slrepo.findAll()) {
				if (Materiel.getSalle().getId() == sl.getId()) {

					repo.save(Materiel);
					log.info("the created Materiel is :" + Materiel);
				} else {
					log.info("");
				}
			}
		}

		return Materiel;

	}

	public Optional<Materiel> getById(Long id) {
		return repo.findById(id);

	}

	public void delete(Long id) throws MaterielNotFoundException {
		if (getById(id).isPresent()) {
			repo.deleteById(id);
			log.info("the removed materiels is :" + getById(id));
			log.info("number of materiels :" + repo.count());
		} else {
			throw new MaterielNotFoundException();
		}

	}

	public void update(Materiel mat) throws MaterielNullException, MaterielNotFoundException {
		Boolean trouve = false;
		if (mat.getLibelle() == null || mat.getCategortie() == null || mat.getId() == null || mat.getSalle() == null
				|| mat.getLibelle() == null && mat.getCategortie() == null && mat.getId() == null
						&& mat.getSalle() == null) {
			throw new MaterielNullException();
		} else if (getById(mat.getId()).isPresent()) {
			repo.save(mat);
			trouve = true;
		}
		if (trouve == false) {
			throw new MaterielNotFoundException();
		}
	}

	public List<Materiel> getAllMateriels() {
		return (List<Materiel>) repo.findAll();
	}

}
