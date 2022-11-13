package com.application.locationVoiture.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.application.locationVoiture.Entities.Manager;

public interface ManagerRepository extends JpaRepository<Manager,Integer> {
	@Query("SELECT M FROM Manager M where id = :id")
	Manager getMg(int id);
}
