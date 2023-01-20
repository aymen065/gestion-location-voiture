package com.application.locationVoiture.Entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Prix")
public class Prix implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5871077151941382682L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private float valeur;
private float garantie;
private Date dateDeb ;
private Date dateFin;
@ManyToOne
private Categorie categorie;

public Prix() {
	super();
}
public Prix(int id, float valeur, float garantie, Date dateDeb, Date dateFin, Categorie categorie) {
	super();
	this.id = id;
	this.valeur = valeur;
	this.garantie = garantie;
	this.dateDeb = dateDeb;
	this.dateFin = dateFin;
	this.categorie = categorie;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public float getValeur() {
	return valeur;
}
public void setValeur(float valeur) {
	this.valeur = valeur;
}
public float getGarantie() {
	return garantie;
}
public void setGarantie(float garantie) {
	this.garantie = garantie;
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
public Categorie getCategorie() {
	return categorie;
}
public void setCategorie(Categorie categorie) {
	this.categorie = categorie;
}

}
