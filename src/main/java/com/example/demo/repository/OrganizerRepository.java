package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Organizer;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Long> {

	boolean existsByNameAndContactEmail(String name, String contactEmail);

}
