package com.application.locationVoiture.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.application.locationVoiture.Entities.Constructeur;

public interface ConstructeurRepository extends JpaRepository<Constructeur, Integer> {

	@Query("SELECT C FROM Constructeur C where nom = :nom")
	Constructeur getConst(String nom);
}
