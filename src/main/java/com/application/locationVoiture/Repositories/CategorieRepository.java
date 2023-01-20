package com.application.locationVoiture.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.application.locationVoiture.Entities.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie,Integer> {
	@Query("SELECT C FROM Categorie C where nom = :nom")
	Categorie getCat(String nom);

}
