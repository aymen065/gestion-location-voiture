package com.application.locationVoiture.Entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "Reservation")
public class Reservation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5406757230390419669L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Time heure;
	private Date dateDeb;
	private Date dateFin;
	@ManyToOne
	@JsonIgnoreProperties({"adresse","reservations"})
	private Client client;
	@ManyToOne
	@JsonIgnoreProperties({"reservations","accidents"})
	private Voiture voiture;
	
	public Reservation() {
		super();
	}
	public Reservation(int id, Date dateDeb, Date dateFin, Client client, Voiture voiture) {
		super();
		this.id = id;
		this.dateDeb = dateDeb;
		this.dateFin = dateFin;
		this.client = client;
		this.voiture = voiture;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Voiture getVoiture() {
		return voiture;
	}
	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}
	public Time getHeure() {
		return heure;
	}
	public void setHeure(Time heure) {
		this.heure = heure;
	}
	
	
}
