package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.OrganizerRepository;
import com.example.demo.model.Organizer;

@Service
public class OrganizerService {

	
	 private final OrganizerRepository organizerRepository;

	    @Autowired
	    public OrganizerService(OrganizerRepository organizerRepository) {
	        this.organizerRepository = organizerRepository;
	    }

	    public List<Organizer> getAllOrganizers() {
	        return organizerRepository.findAll();
	    }

	    public Organizer getOrganizerById(long id) {   	
	        return organizerRepository.findById(id).orElse(null);
	        
	        
	    }

	    public Organizer createOrganizer(Organizer organizer) {
	    	
	    	if (organizerRepository.existsByNameAndContactEmail(organizer.getName(), organizer.getContactEmail())) {
	            // Duplicate organizer found
	            return null;
	        } else {
	            // No duplicate organizer found, proceed to save
	            return organizerRepository.save(organizer);
	        }
	    }

	    public Organizer updateOrganizer(long id, Organizer updatedOrganizer) {
	        Organizer existingOrganizer = organizerRepository.findById(id).orElse(null);

	        if (existingOrganizer != null) {
	            // Organizer found, proceed with the update
	            existingOrganizer.setName(updatedOrganizer.getName());
	            existingOrganizer.setContactEmail(updatedOrganizer.getContactEmail());
	            existingOrganizer.setContactPhone(updatedOrganizer.getContactPhone());
	            return organizerRepository.save(existingOrganizer);
	        } else {
	            // Organizer not found, handle the situation accordingly
	            return null; 
	        }
	    }

	    public boolean deleteOrganizer(Long id) {
	    	Organizer existingOrganizer = organizerRepository.findById(id).orElse(null);

	        if (existingOrganizer != null) {
	            // Organizer found, proceed with the deletion
	            organizerRepository.delete(existingOrganizer);
	            return true;
	        } else {
	            // Organizer not found, return false
	            return false;
	        }

	    }
	
}
