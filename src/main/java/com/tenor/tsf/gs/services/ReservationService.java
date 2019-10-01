package com.tenor.tsf.gs.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenor.tsf.gs.entity.Reservation;
import com.tenor.tsf.gs.exception.ReservationAlreadyreservedException;
import com.tenor.tsf.gs.exception.ReservationDateException;
import com.tenor.tsf.gs.exception.ReservationNotFoundException;
import com.tenor.tsf.gs.exception.ReservationNullException;
import com.tenor.tsf.gs.repositories.ReservationRepo;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@Transactional
public class ReservationService {
	@Autowired
	ReservationRepo repo;
	private LocalDateTime date = LocalDateTime.now();

	public Reservation create(Reservation Reservation)
			throws ReservationNullException, ReservationDateException, ReservationAlreadyreservedException {
		if (Reservation.getDateDebut() == null || Reservation.getDateFin() == null || Reservation.getSalle() == null
				|| Reservation.getUser() == null
				|| Reservation.getDateDebut() == null && Reservation.getDateFin() == null
						&& Reservation.getSalle() == null && Reservation.getUser() == null) {
			throw new ReservationNullException();
		} else if (Reservation.getDateDebut().isBefore(date)
				|| Reservation.getDateFin().isBefore(Reservation.getDateDebut())) {
			throw new ReservationDateException();
		} else if (CheckRoomAvialable(Reservation.getSalle().getId(), Reservation.getDateDebut(),
				Reservation.getDateFin()) == false) {
			throw new ReservationAlreadyreservedException();
		} else {
			repo.save(Reservation);
			log.info("the created Reservation is :" + Reservation);

		}

		return Reservation;
	}

	public Optional<Reservation> getById(Long id) {
		return repo.findById(id);

	}

	public void delete(Long id) throws ReservationNotFoundException {
		if (getById(id).isPresent()) {
			repo.deleteById(id);
			log.info("the removed Reservation is :" + getById(id));
			log.info("number of Reservations :" + repo.count());
		} else {
			throw new ReservationNotFoundException();
		}

	}

	public void update(Reservation res)
			throws ReservationNotFoundException, ReservationNullException, ReservationDateException {
		Boolean trouve = false;
		if (res.getDateDebut() == null || res.getDateFin() == null || res.getId() == null || res.getSalle() == null
				|| res.getUser() == null || res.getDateDebut() == null && res.getDateFin() == null
						&& res.getId() == null && res.getSalle() == null && res.getUser() == null) {
			throw new ReservationNullException();
		} else if (res.getDateDebut().isBefore(date) || res.getDateFin().isBefore(res.getDateDebut())) {
			throw new ReservationDateException();
		} else if (getById(res.getId()).isPresent()) {
			repo.save(res);
			log.info("the updated Reservation is :" + res);
			trouve = true;

		}
		if (trouve == false)

		{
			throw new ReservationNotFoundException();
		}
	}

	public Boolean CheckRoomAvialable(Long id, LocalDateTime dateDE, LocalDateTime dateFE)
			throws ReservationAlreadyreservedException {
		for (Reservation res : repo.findAll()) {
			if (res.getSalle().getId() == id) {
				if (dateDE.isBefore(res.getDateFin()) && dateDE.isAfter(res.getDateDebut())
						|| dateFE.isAfter(res.getDateDebut()) && dateFE.isBefore(res.getDateFin())
						|| dateDE.isEqual(res.getDateDebut()) || dateFE.isEqual(res.getDateFin())
						|| dateDE.isBefore(res.getDateDebut()) && dateFE.isAfter(res.getDateFin())) {
					throw new ReservationAlreadyreservedException();
				}
			}
		}
		log.info("Room number " + id + " is avilable in the time between --" + dateDE + "-- and --" + dateFE + "--");
		return true;

	}

	public List<Reservation> getAllReservation() {
		return (List<Reservation>) repo.findAll();
	}
}
