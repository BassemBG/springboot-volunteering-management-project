package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.EmbeddedEnrollmentId;
import com.example.demo.model.Organizer;
import com.example.demo.model.OrganizerVolunteerEnrollment;
import com.example.demo.repository.OrganizerRepository;
import com.example.demo.repository.OrganizerVolunteerEnrollmentRepository;
import com.example.demo.repository.VolunteerRepository;
import com.example.demo.utils.EnrollmentCreationResponse;

@Service
public class OrganizerVolunteerEnrollmentService {

	private final OrganizerVolunteerEnrollmentRepository organizerVolunteerEnrollmentRepository;
	private final OrganizerRepository organizerRepository;
	private final VolunteerRepository volunteerRepository;

    @Autowired
    public OrganizerVolunteerEnrollmentService(OrganizerVolunteerEnrollmentRepository enrollmentRepository, OrganizerRepository organizerRepository, VolunteerRepository volunteerRepository) {
        this.organizerVolunteerEnrollmentRepository = enrollmentRepository;
        this.organizerRepository = organizerRepository;
        this.volunteerRepository = volunteerRepository;
    }
    
    public List<OrganizerVolunteerEnrollment> getAllOrganizerVolunteerEnrollments() {
        return organizerVolunteerEnrollmentRepository.findAll();
    }

    public OrganizerVolunteerEnrollment getOrganizerVolunteerEnrollmentById(Long organizerId, Long volunteerId) {   	
        
        EmbeddedEnrollmentId enrollmentId = new EmbeddedEnrollmentId(organizerId, volunteerId);
        return organizerVolunteerEnrollmentRepository.findById(enrollmentId).orElse(null);
        
        
    }

    

    public OrganizerVolunteerEnrollment updateOrganizerVolunteerEnrollment(Long organizerId, Long volunteerId, OrganizerVolunteerEnrollment updatedOrganizerVolunteerEnrollment) {
        EmbeddedEnrollmentId enrollmentId = new EmbeddedEnrollmentId(organizerId, volunteerId);
    	OrganizerVolunteerEnrollment existingOrganizerVolunteerEnrollment = organizerVolunteerEnrollmentRepository.findById(enrollmentId).orElse(null);

        if (existingOrganizerVolunteerEnrollment != null) {
            // OrganizerVolunteerEnrollment found, proceed with the update
            existingOrganizerVolunteerEnrollment.setRole(updatedOrganizerVolunteerEnrollment.getRole());
            existingOrganizerVolunteerEnrollment.setHoursContributed(updatedOrganizerVolunteerEnrollment.getHoursContributed());
            existingOrganizerVolunteerEnrollment.setStatus(updatedOrganizerVolunteerEnrollment.getStatus());
            return organizerVolunteerEnrollmentRepository.save(existingOrganizerVolunteerEnrollment);
        } else {
            // OrganizerVolunteerEnrollment not found, handle the situation accordingly
            return null; 
        }
    }

    public EnrollmentCreationResponse createEnrollment(OrganizerVolunteerEnrollment enrollment) {

    	Long organizerId = enrollment.getOrganizer().getOrganizerId();
        Long volunteerId = enrollment.getVolunteer().getId();

        // Check if the organizerId and volunteerId exist in their respective tables
        if (organizerRepository.existsById(organizerId) && volunteerRepository.existsById(volunteerId)) {
            // Check if the enrollment already exists
            EmbeddedEnrollmentId enrollmentId = new EmbeddedEnrollmentId(organizerId, volunteerId);
            if (!organizerVolunteerEnrollmentRepository.existsById(enrollmentId)) {
            	organizerVolunteerEnrollmentRepository.save(enrollment);
                return new EnrollmentCreationResponse(true, "Volunteer successfully enrolled !"); // Success
            } else {
                return new EnrollmentCreationResponse(false, "Enrollment already exists");
            }
        } else {
            return new EnrollmentCreationResponse(false, "Organizer or Volunteer not found");
        }

    }
    
    
    
    public boolean deleteOrganizerVolunteerEnrollment(Long organizerId, Long volunteerId) {
        EmbeddedEnrollmentId enrollmentId = new EmbeddedEnrollmentId(organizerId, volunteerId);

        OrganizerVolunteerEnrollment existingOrganizerVolunteerEnrollment = organizerVolunteerEnrollmentRepository.findById(enrollmentId).orElse(null);

        if (existingOrganizerVolunteerEnrollment != null) {
            // OrganizerVolunteerEnrollment found, proceed with the deletion
        	organizerVolunteerEnrollmentRepository.delete(existingOrganizerVolunteerEnrollment);
            return true;
        } else {
            // OrganizerVolunteerEnrollment not found, return false
            return false;
        }
    }

    
}
