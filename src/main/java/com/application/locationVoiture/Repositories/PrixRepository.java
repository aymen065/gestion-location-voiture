package com.application.locationVoiture.Repositories;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.application.locationVoiture.Entities.Categorie;
import com.application.locationVoiture.Entities.Prix;

public interface PrixRepository extends JpaRepository<Prix, Integer> {
	@Query("SELECT P from Prix P where P.categorie = :categorie AND :dateDeb BETWEEN P.dateDeb and P.dateFin OR :dateFin BETWEEN P.dateDeb and P.dateFin OR :dateDeb <= P.dateDeb And :dateFin >= P.dateFin")
	Prix getPx(Categorie categorie, Date dateDeb, Date dateFin);
}
