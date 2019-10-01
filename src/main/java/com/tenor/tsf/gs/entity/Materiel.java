package com.tenor.tsf.gs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
@Entity
public @Data class Materiel {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String libelle;
	private String categortie;
	@ManyToOne
	private Salle salle;

}
