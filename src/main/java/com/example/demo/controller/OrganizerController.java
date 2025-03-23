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

import com.example.demo.model.Organizer;
import com.example.demo.service.OrganizerService;

@RestController
@RequestMapping("/RestApi/AideHumanitaire/organizers")
public class OrganizerController {

	private final OrganizerService organizerService;

    @Autowired
    public OrganizerController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    @GetMapping
    public ResponseEntity<List<Organizer>> getAllOrganizers() {
    	try {
            return ResponseEntity.status(HttpStatus.OK).body(organizerService.getAllOrganizers());
    	}catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organizer> getOrganizerById(@PathVariable Long id) {
    	try {
    		Organizer organizer = organizerService.getOrganizerById(id);

            if(organizer != null) {
            	return ResponseEntity.status(HttpStatus.OK).body(organizer);
            }else {
            	return ResponseEntity.status(HttpStatus.NO_CONTENT).body(organizer);

            }
    	}catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
        
    }

    @PostMapping
    public ResponseEntity<Object> createOrganizer(@RequestBody Organizer organizer) {
    	try {
    		Organizer createdOrganizer = organizerService.createOrganizer(organizer);

    	    if (createdOrganizer != null) {
    	        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrganizer);
    	    } else {
    	        return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate organizer found");
    	    }
            
    	}catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    }

    @PutMapping("/{id}")
    public ResponseEntity<Organizer> updateOrganizer(@PathVariable Long id, @RequestBody Organizer updatedOrganizer) {
        
    	try {
    		Organizer updatedEntity = organizerService.updateOrganizer(id, updatedOrganizer);
            
            return updatedEntity != null ? ResponseEntity.ok(updatedEntity) : ResponseEntity.notFound().build();
        
    	}catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    	}
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOrganizer(@PathVariable Long id) {
    	try {
    	    boolean deleted = organizerService.deleteOrganizer(id);
            return deleted ? ResponseEntity.ok("Deleted Successfully !") : ResponseEntity.notFound().build();		
    	}catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    }
	
}
