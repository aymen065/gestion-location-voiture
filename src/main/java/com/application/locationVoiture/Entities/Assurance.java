package com.application.locationVoiture.Entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "Assurence")
public class Assurance implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8027618014756654051L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int numero;
	private String nomAgence;
	private float montant;
	private Date dateDeb;
	private Date dateFin;
	@ManyToOne
	@JsonIgnoreProperties({ "reservations" , "accidents"})
	private Voiture voiture;

	public Assurance() {
		super();
	}

	public Assurance(int numero, String nomAgence, float montant, Date dateDeb, Date dateFin, Voiture voiture) {
		super();
		this.numero = numero;
		this.nomAgence = nomAgence;
		this.montant = montant;
		this.dateDeb = dateDeb;
		this.dateFin = dateFin;
		this.voiture = voiture;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNomAgence() {
		return nomAgence;
	}

	public void setNomAgence(String nomAgence) {
		this.nomAgence = nomAgence;
	}

	public float getMontant() {
		return montant;
	}

	public void setMontant(float montant) {
		this.montant = montant;
	}

	public Date getDateDeb() {
		return dateDeb;
	}

	public void setDateDeb(Date dateDeb) {
		this.dateDeb = dateDeb;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Voiture getVoiture() {
		return voiture;
	}

	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}

}
