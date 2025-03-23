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

import com.example.demo.model.Volunteer;
import com.example.demo.service.VolunteerService;

@RestController
@RequestMapping("/RestApi/AideHumanitaire/volunteers")
public class VolunteerController {

	
	private final VolunteerService VolunteerService;

    @Autowired
    public VolunteerController(VolunteerService volunteerService) {
        this.VolunteerService = volunteerService;
    }

    @GetMapping
    public ResponseEntity<List<Volunteer>> getAllVolunteers() {
    	try {
            return ResponseEntity.status(HttpStatus.OK).body(VolunteerService.getAllVolunteers());
    	}catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    }

    @GetMapping("/{id}")
    public ResponseEntity<Volunteer> getVolunteerById(@PathVariable Long id) {
    	try {
    		Volunteer Volunteer = VolunteerService.getVolunteerById(id);

            if(Volunteer != null) {
            	return ResponseEntity.status(HttpStatus.OK).body(Volunteer);
            }else {
            	return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Volunteer);

            }
    	}catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
        
    }

    @PostMapping
    public ResponseEntity<Object> createVolunteer(@RequestBody Volunteer Volunteer) {
    	try {
    		Volunteer createdVolunteer = VolunteerService.createVolunteer(Volunteer);

    	    if (createdVolunteer != null) {
    	        return ResponseEntity.status(HttpStatus.CREATED).body(createdVolunteer);
    	    } else {
    	        return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate Volunteer found");
    	    }
            
    	}catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    }

    @PutMapping("/{id}")
    public ResponseEntity<Volunteer> updateVolunteer(@PathVariable Long id, @RequestBody Volunteer updatedVolunteer) {
        
    	try {
    		Volunteer updatedEntity = VolunteerService.updateVolunteer(id, updatedVolunteer);
            
            return updatedEntity != null ? ResponseEntity.ok(updatedEntity) : ResponseEntity.notFound().build();
        
    	}catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    	}
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVolunteer(@PathVariable Long id) {
    	try {
    	    boolean deleted = VolunteerService.deleteVolunteer(id);
            return deleted ? ResponseEntity.ok("Deleted Successfully !") : ResponseEntity.notFound().build();		
    	}catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    }
	
}
