package com.application.locationVoiture.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.application.locationVoiture.Entities.Voiture;

public interface VoitureRepository extends JpaRepository<Voiture,String>{
@Query("SELECT V FROM Voiture V WHERE matricule = :matricule")
Voiture GetVtr(String matricule);
@Query("SELECT V FROM Voiture V WHERE marque.nom = :nom")
List<Voiture> GetVtrParMarque(String nom);

@Query("SELECT V FROM Voiture V WHERE marque.constructeur.nom = :nom")
List<Voiture> GetVtrParConst(String nom);
@Query("SELECT V FROM Voiture V WHERE categorie.nom = :nom")
List<Voiture> GetVtrParCat(String nom);

@Query("SELECT COUNT(V) FROM Voiture V")
int getNbVtr();
}
