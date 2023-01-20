package com.application.locationVoiture.Entities;

import java.io.Serializable;
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
@Table(name = "Marque")
public class Marque implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7188327788749973449L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	@OneToMany(fetch = FetchType.EAGER, targetEntity = Voiture.class)
	@JoinColumn(name = "marque_id")
	@JsonIgnoreProperties({"marque" , "categorie"})
	private Set<Voiture> voitures;
	@ManyToOne
	private Constructeur constructeur;
	
	public Marque() {
		super();
	}
	public Marque(int id, String nom, Set<Voiture> voitures) {
		super();
		this.id = id;
		this.nom = nom;
		this.voitures = voitures;
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
	public Constructeur getConstructeur() {
		return constructeur;
	}
	public void setConstructeur(Constructeur constructeur) {
		this.constructeur = constructeur;
	}
	
}
