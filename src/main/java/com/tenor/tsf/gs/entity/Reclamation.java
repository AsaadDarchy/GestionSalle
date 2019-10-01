package com.tenor.tsf.gs.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.tenor.tsf.gs.enumeration.Statut;

import lombok.Data;
@Entity
public @Data class Reclamation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime dateRec;
	@OneToOne
	private User user;
	@OneToOne
	private Salle salle;
	private Statut Statu;
	private String message;

}
