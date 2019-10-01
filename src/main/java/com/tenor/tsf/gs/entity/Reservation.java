package com.tenor.tsf.gs.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
public @Data class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime dateDebut;
	private LocalDateTime dateFin;
	@OneToOne
	private User user;
	@OneToOne
	private Salle salle;

}
