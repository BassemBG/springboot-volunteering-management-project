package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Volunteer;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

	boolean existsByNameAndContactEmail(String name, String contactEmail);
	
}
