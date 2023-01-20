package com.application.locationVoiture.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.locationVoiture.Entities.Accident;

public interface AccidentRepository extends JpaRepository<Accident, Integer>{

}
