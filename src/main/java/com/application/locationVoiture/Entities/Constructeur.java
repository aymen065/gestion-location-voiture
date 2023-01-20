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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Constructeur")
public class Constructeur implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 917248167557142126L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	@OneToMany(fetch = FetchType.EAGER, targetEntity = Marque.class)
	@JoinColumn(name = "constructeur_id")
	@JsonIgnoreProperties({"constructeur"})
	private Set<Marque> marques;
	
	public Constructeur() {
		super();
	}
	public Constructeur(int id, String nom, Set<Marque> marques) {
		super();
		this.id = id;
		this.nom = nom;
		this.marques = marques;
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
	public Set<Marque> getMarques() {
		return marques;
	}
	public void setMarques(Set<Marque> marques) {
		this.marques = marques;
	}
	
}
