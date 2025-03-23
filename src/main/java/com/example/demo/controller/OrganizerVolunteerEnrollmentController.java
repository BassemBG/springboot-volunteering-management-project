package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.OrganizerVolunteerEnrollment;
import com.example.demo.service.OrganizerVolunteerEnrollmentService;
import com.example.demo.utils.EnrollmentCreationResponse;

@RestController
@RequestMapping("/RestApi/AideHumanitaire/enrollments")
public class OrganizerVolunteerEnrollmentController {

	private final OrganizerVolunteerEnrollmentService enrollmentService;

    @Autowired
    public OrganizerVolunteerEnrollmentController(OrganizerVolunteerEnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    
    @GetMapping
    public ResponseEntity<List<OrganizerVolunteerEnrollment>> getAllEnrollments() {
        try {
            List<OrganizerVolunteerEnrollment> enrollments = enrollmentService.getAllOrganizerVolunteerEnrollments();
            return ResponseEntity.ok(enrollments);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/{organizerId}/{volunteerId}")
    public ResponseEntity<OrganizerVolunteerEnrollment> getEnrollmentById(
            @PathVariable Long organizerId,
            @PathVariable Long volunteerId
    ) {
        try {
            OrganizerVolunteerEnrollment enrollment = enrollmentService.getOrganizerVolunteerEnrollmentById(organizerId, volunteerId);
            if (enrollment != null) {
                return ResponseEntity.ok(enrollment);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    
    
    @PostMapping
    public ResponseEntity<Object> createEnrollment(
            @RequestBody OrganizerVolunteerEnrollment enrollment
    ) {
    	try {
    	
    		EnrollmentCreationResponse response = enrollmentService.createEnrollment(enrollment);

            if (response.isSuccess()) {
                return ResponseEntity.ok(response);
            } else {
                // Return a specific HTTP status code based on the reason for failure
                if ("Enrollment already exists".equals(response.getErrorMessage())) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(response); // Conflict status code
                } else {
                    return ResponseEntity.status(401).body(response); // Bad Request status code
                }
            }
    		
    	}catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            
    	}
        
    }
    
    
    @PutMapping("/{organizerId}/{volunteerId}")
    public ResponseEntity<Object> updateEnrollment(
            @PathVariable Long organizerId,
            @PathVariable Long volunteerId,
            @RequestBody OrganizerVolunteerEnrollment updatedEnrollment
    ) {
        try {
            OrganizerVolunteerEnrollment updated = enrollmentService.updateOrganizerVolunteerEnrollment(organizerId, volunteerId, updatedEnrollment);
            if (updated != null) {
                return ResponseEntity.ok("Updated succesfully !");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).build();
        }
    }
    
    
    @DeleteMapping("/{organizerId}/{volunteerId}")
    public ResponseEntity<Object> deleteEnrollment(
            @PathVariable Long organizerId,
            @PathVariable Long volunteerId
    ) {
        try {
            boolean deleted = enrollmentService.deleteOrganizerVolunteerEnrollment(organizerId, volunteerId);
            if (deleted) {
                return ResponseEntity.ok("Successfully Deleted !"); // 204 No Content
            } else {
                return ResponseEntity.notFound().build(); // 404 Not Found
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).build(); // 500 Internal Server Error
        }
    }
    
	
	
}
