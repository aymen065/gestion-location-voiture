package com.application.locationVoiture.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.application.locationVoiture.Entities.Adresse;

public interface AdresseRepository extends JpaRepository<Adresse,Integer>{
	@Query("SELECT A FROM Adresse A where ville = :ville")
	Adresse getAdr(String ville);
	
	@Query("SELECT A FROM Adresse A where ville = :ville AND rue = :rue")
	Adresse getAdr(String ville , String rue);
}
