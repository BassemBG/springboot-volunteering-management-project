package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Volunteer;
import com.example.demo.repository.VolunteerRepository;

@Service
public class VolunteerService {

	private final VolunteerRepository VolunteerRepository;

    @Autowired
    public VolunteerService(VolunteerRepository VolunteerRepository) {
        this.VolunteerRepository = VolunteerRepository;
    }

    public List<Volunteer> getAllVolunteers() {
        return VolunteerRepository.findAll();
    }

    public Volunteer getVolunteerById(long id) {   	
        return VolunteerRepository.findById(id).orElse(null);
        
        
    }

    public Volunteer createVolunteer(Volunteer Volunteer) {
    	
    	if (VolunteerRepository.existsByNameAndContactEmail(Volunteer.getName(), Volunteer.getContactEmail())) {
            // Duplicate Volunteer found
            return null;
        } else {
            // No duplicate Volunteer found, proceed to save
            return VolunteerRepository.save(Volunteer);
        }
    }

    public Volunteer updateVolunteer(long id, Volunteer updatedVolunteer) {
        Volunteer existingVolunteer = VolunteerRepository.findById(id).orElse(null);

        if (existingVolunteer != null) {
            // Volunteer found, proceed with the update
            existingVolunteer.setName(updatedVolunteer.getName());
            existingVolunteer.setContactEmail(updatedVolunteer.getContactEmail());
            existingVolunteer.setContactPhone(updatedVolunteer.getContactPhone());
            return VolunteerRepository.save(existingVolunteer);
        } else {
            // Volunteer not found, handle the situation accordingly
            return null; 
        }
    }

    public boolean deleteVolunteer(Long id) {
    	Volunteer existingVolunteer = VolunteerRepository.findById(id).orElse(null);

        if (existingVolunteer != null) {
            // Volunteer found, proceed with the deletion
            VolunteerRepository.delete(existingVolunteer);
            return true;
        } else {
            // Volunteer not found, return false
            return false;
        }

    }
	
	
}
