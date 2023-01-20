package com.application.locationVoiture.Repositories;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.application.locationVoiture.Entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation,Integer>{

	@Query("SELECT R from Reservation R "
			+ "where R.voiture.matricule = :matricule "
			+ "AND (( :dateDeb BETWEEN R.dateDeb and R.dateFin) OR"
			+ " ( :dateFin BETWEEN R.dateDeb and R.dateFin) OR"
			+ " ( :dateDeb <= R.dateDeb And :dateFin >= R.dateFin))")
	Reservation getRes(String matricule , Date dateDeb , Date dateFin);

	@Query("SELECT R from Reservation R "
			+ "where R.voiture.matricule = :matricule "
			+ "AND :dateDeb = R.dateDeb AND"
			+ "  :dateFin = R.dateFin")
	Reservation getResAnnulee(String matricule , Date dateDeb , Date dateFin);
	@Query("SELECT COUNT(R) FROM Reservation R WHERE dateFin > :dateJour")
	int getNbRes(Date dateJour);
}
