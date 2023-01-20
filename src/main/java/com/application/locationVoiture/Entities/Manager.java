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
@Table(name = "Manager")
public class Manager implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6098033881374310043L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String prenom;
	private long tel;
	private String mail;
	private Date dateNaiss;
	@ManyToOne
	private Adresse adresse;
	@OneToMany(fetch = FetchType.EAGER, targetEntity = Accident.class)
	@JoinColumn(name = "Manager_id")
	@JsonIgnoreProperties({"manager"})
	private Set<Accident> accidents;
	
	public Manager() {
		super();
	}
	public Manager(int id, String nom, String prenom, long tel, String mail, Date dateNaiss, Adresse adresse,
			Set<Accident> accidents) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.mail = mail;
		this.dateNaiss = dateNaiss;
		this.adresse = adresse;
		this.accidents = accidents;
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
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
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
	
	
}
