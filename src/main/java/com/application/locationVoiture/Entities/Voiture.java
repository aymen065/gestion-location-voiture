package com.application.locationVoiture.Entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Voiture")
public class Voiture implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2549723494319695328L;
	@Id
	private String matricule;
	private Date dateMiseEnCirculation;
	private String proprietaire;
	@OneToMany(fetch = FetchType.EAGER, targetEntity = Reservation.class)
	@JoinColumn(name = "voiture_matricule")
	@JsonIgnoreProperties({"voiture","client"})
	private Set<Reservation> reservations;
	@OneToMany(fetch = FetchType.EAGER, targetEntity = Accident.class)
	@JoinColumn(name = "voiture_matricule")
	@JsonIgnoreProperties({"voiture","client","manager"})
	private Set<Accident> accidents;
	@ManyToOne
	@JsonIgnoreProperties({"voiture"})
	private Marque marque;
	@ManyToOne
	@JsonIgnoreProperties({"voiture"})
	private Categorie categorie;
	@OneToMany(fetch = FetchType.EAGER, targetEntity = Assurance.class)
	@JoinColumn(name = "voiture_matricule")
	@JsonIgnoreProperties({"voiture"})
	private Set<Assurance> assurances;

	
	public Voiture() {
		super();
	}

	public Voiture(String matricule, Date dateMiseEnCirculation, String proprietaire, Set<Reservation> reservations,
			Set<Accident> accidents, Marque marque, Categorie categorie, Set<Assurance> assurances) {
		super();
		this.matricule = matricule;
		this.dateMiseEnCirculation = dateMiseEnCirculation;
		this.proprietaire = proprietaire;
		this.reservations = reservations;
		this.accidents = accidents;
		this.marque = marque;
		this.categorie = categorie;
		this.assurances = assurances;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public Date getDateMiseEnCirculation() {
		return dateMiseEnCirculation;
	}

	public void setDateMiseEnCirculation(Date dateMiseEnCirculation) {
		this.dateMiseEnCirculation = dateMiseEnCirculation;
	}

	public String getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Set<Accident> getAccidents() {
		return accidents;
	}

	public void setAccidents(Set<Accident> accidents) {
		this.accidents = accidents;
	}

	public Marque getMarque() {
		return marque;
	}

	public void setMarque(Marque marque) {
		this.marque = marque;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Set<Assurance> getAssurances() {
		return assurances;
	}

	public void setAssurances(Set<Assurance> assurances) {
		this.assurances = assurances;
	}

}
