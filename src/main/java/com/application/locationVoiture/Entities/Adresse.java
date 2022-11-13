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
@Table(name = "Adresse")
public class Adresse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -501517857529458176L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String ville;
	private String rue;
	@OneToMany(fetch = FetchType.EAGER, targetEntity = Client.class)
	@JoinColumn(name = "adresse_id")
	@JsonIgnoreProperties({"adresse"})
	private Set<Client> clients;
	@OneToMany(fetch = FetchType.EAGER, targetEntity = Manager.class)
	@JoinColumn(name = "adresse_id")
	@JsonIgnoreProperties({"adresse"})
	private Set<Manager> managers;
	
	public Adresse() {
		super();
	}
	public Adresse(int id, String ville, String rue, Set<Client> clients, Set<Manager> managers) {
		super();
		this.id = id;
		this.ville = ville;
		this.rue = rue;
		this.clients = clients;
		this.managers = managers;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public Set<Client> getClients() {
		return clients;
	}
	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}
	public Set<Manager> getManagers() {
		return managers;
	}
	public void setManagers(Set<Manager> managers) {
		this.managers = managers;
	}
	
	
}
