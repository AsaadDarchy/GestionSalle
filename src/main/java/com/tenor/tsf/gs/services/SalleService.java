package com.tenor.tsf.gs.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenor.tsf.gs.entity.Reservation;
import com.tenor.tsf.gs.entity.Salle;
import com.tenor.tsf.gs.exception.SalleNotFoundException;
import com.tenor.tsf.gs.exception.SalleNullException;
import com.tenor.tsf.gs.repositories.ReservationRepo;
import com.tenor.tsf.gs.repositories.SalleRepo;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@Transactional
public class SalleService {
	@Autowired
	SalleRepo repo;
	@Autowired
	ReservationRepo resrepo;

	public Salle create(Salle Salle) throws SalleNullException {
		if (Salle.getLibelle() == null || Salle.getCapacite() == null
				|| Salle.getLibelle() == null && Salle.getCapacite() == null) {
			throw new SalleNullException();
		} else {
			repo.save(Salle);
			log.info("the created Salle is :" + Salle);
		}
		return Salle;

	}

	public Optional<Salle> getById(Long id) {
		return repo.findById(id);

	}

	public void delete(Long id) throws SalleNotFoundException {
		if (getById(id).isPresent()) {
			repo.deleteById(id);
			;
			log.info("the removed room is :" + getById(id));
			log.info("number of rooms :" + repo.count());
		} else {
			throw new SalleNotFoundException();
		}
	}

	public void update(Salle sal) throws SalleNotFoundException, SalleNullException {
		Boolean trouve = false;
		if (sal.getLibelle() == null || sal.getCapacite() == null || sal.getId() == null
				|| sal.getLibelle() == null && sal.getCapacite() == null && sal.getId() == null) {
			throw new SalleNullException();
		} else {
				if (getById(sal.getId()).isPresent()) {
					repo.save(sal);
					log.info("updated successfully :" + sal);
					trouve = true;
				}

		}
		if (trouve == false) {
			throw new SalleNotFoundException();
		}

	}

	public List<Salle> AllAvailableRooms(LocalDateTime dateDE, LocalDateTime dateFE) {
		List<Salle> RoomsAvailable = (List<Salle>) repo.findAll();
		for (Salle rooms : RoomsAvailable) {
			for (Reservation res : resrepo.findAll()) {
				if (res.getSalle().getId().equals(rooms.getId())) {
					if (dateDE.isBefore(res.getDateFin()) && dateDE.isAfter(res.getDateDebut())
							|| dateFE.isAfter(res.getDateDebut()) && dateFE.isBefore(res.getDateFin())
							|| dateDE.isEqual(res.getDateDebut()) || dateFE.isEqual(res.getDateFin())) {
						RoomsAvailable.remove(rooms);
						log.info("there is no avialable rooms at The given time and date ");

					}
				}
			}
		}
		log.info("the available rooms are below : ");
		return RoomsAvailable;
	}

	public List<Salle> geAllRooms() {
		return (List<Salle>) repo.findAll();

	}
}
