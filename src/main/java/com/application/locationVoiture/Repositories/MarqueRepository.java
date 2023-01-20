package com.application.locationVoiture.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.application.locationVoiture.Entities.Marque;

public interface MarqueRepository extends JpaRepository<Marque,Integer>{

	@Query("SELECT M FROM Marque M where nom = :nom")
	Marque getMq(String nom);
}
