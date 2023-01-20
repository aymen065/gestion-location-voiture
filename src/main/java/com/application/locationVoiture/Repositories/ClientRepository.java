package com.application.locationVoiture.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.application.locationVoiture.Entities.Adresse;
import com.application.locationVoiture.Entities.Client;


public interface ClientRepository extends JpaRepository<Client,Integer>{
@Query("SELECT C FROM Client C where tel = :tel")
Client getClt(long tel);

@Query("SELECT C FROM Client C where tel = :tel and id = :id")
Client getClt(long tel , int id);

@Query("SELECT C FROM Client C where id = :id")
Client getCltParId(int id);

@Query("SELECT C FROM Client C where nom = :nom")
List<Client> getCltParNom(String nom);


@Query("SELECT C FROM Client C where prenom = :prenom")
List<Client> getCltParPrenom(String prenom);



List<Client>getByAdresse(Adresse adresse);
@Query("SELECT COUNT(C) FROM Client C")
int getNbClt();
}
