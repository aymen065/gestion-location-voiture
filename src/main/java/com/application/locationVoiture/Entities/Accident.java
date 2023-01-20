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
@Table(name = "Accident")
public class Accident implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5820253982089998022L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Date date;
	private String Description;
	@ManyToOne
	private Client client;
	@ManyToOne
	@JsonIgnoreProperties({"accidents","reservations","marque","categorie","assurances"})
	private Voiture voiture;
	@ManyToOne
	@JsonIgnoreProperties({"adresse","accidents"})
	private Manager manager;
	
	public Accident() {
		super();
	}
	public Accident(int id, Date date, String description, Client client, Voiture voiture, Manager manager) {
		super();
		this.id = id;
		this.date = date;
		Description = description;
		this.client = client;
		this.voiture = voiture;
		this.manager = manager;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
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
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
}
