package com.application.locationVoiture.Entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "Client")
public class Client implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3557174186201603210L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String prenom;
	private long tel;
	private Date dateNaiss;
	@ManyToOne
	@JsonIgnoreProperties({"clients","managers"})
	private Adresse adresse;
	@OneToMany(fetch = FetchType.EAGER, targetEntity = Accident.class)
	@JoinColumn(name = "client_id")
	@JsonIgnoreProperties({"client"})
	private Set<Accident> accidents;
	@OneToMany(fetch = FetchType.EAGER, targetEntity = Reservation.class)
	@JoinColumn(name = "client_id")
	@JsonIgnoreProperties({"client"})
	private Set<Reservation> reservations;
	
	public Client() {
		super();
	}
	public Client(int id, String nom, String prenom, long tel, Date dateNaiss, Adresse adresse,
			Set<Accident> accidents, Set<Reservation> reservations) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.dateNaiss = dateNaiss;
		this.adresse = adresse;
		this.accidents = accidents;
		this.reservations = reservations;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public long getTel() {
		return tel;
	}
	public void setTel(long tel) {
		this.tel = tel;
	}
	public Date getDateNaiss() {
		return dateNaiss;
	}
	public void setDateNaiss(Date dateNaiss) {
		this.dateNaiss = dateNaiss;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	public Set<Accident> getAccidents() {
		return accidents;
	}
	public void setAccidents(Set<Accident> accidents) {
		this.accidents = accidents;
	}
	public Set<Reservation> getReservations() {
		return reservations;
	}
	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	
}
