package com.application.locationVoiture.Entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "Categorie")
public class Categorie implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8932335490694535965L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	@OneToMany(fetch = FetchType.EAGER, targetEntity = Voiture.class)
	@JoinColumn(name = "categorie_id")
	//@JsonIgnoreProperties(value = "categorie")
	@JsonIgnore
	private Set<Voiture> voitures;
	@OneToMany(fetch = FetchType.EAGER, targetEntity = Prix.class)
	@JoinColumn(name = "categorie_id")
	@JsonIgnoreProperties({"categorie"})
	private Set<Prix> prix;
	
	public Categorie() {
		super();
	}
	public Categorie(int id, String nom, Set<Voiture> voitures, Set<Prix> prix) {
		super();
		this.id = id;
		this.nom = nom;
		this.voitures = voitures;
		this.prix = prix;
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
	public Set<Voiture> getVoitures() {
		return voitures;
	}
	public void setVoitures(Set<Voiture> voitures) {
		this.voitures = voitures;
	}
	public Set<Prix> getPrix() {
		return prix;
	}
	public void setPrix(Set<Prix> prix) {
		this.prix = prix;
	}
	
}
