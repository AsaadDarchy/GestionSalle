package com.tenor.tsf.gs.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tenor.tsf.gs.entity.Reservation;

@Repository
public interface ReservationRepo extends CrudRepository<Reservation, Long> {

}
