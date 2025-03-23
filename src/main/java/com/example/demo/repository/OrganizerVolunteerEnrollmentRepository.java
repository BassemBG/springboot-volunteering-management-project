package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EmbeddedEnrollmentId;
import com.example.demo.model.OrganizerVolunteerEnrollment;

@Repository
public interface OrganizerVolunteerEnrollmentRepository extends JpaRepository<OrganizerVolunteerEnrollment, EmbeddedEnrollmentId> {

}
