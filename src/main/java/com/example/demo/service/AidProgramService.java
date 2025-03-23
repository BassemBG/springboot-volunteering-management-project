package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.example.demo.model.AidProgram;
import com.example.demo.model.Organizer;
import com.example.demo.repository.AidProgramRepository;
import com.example.demo.repository.OrganizerRepository;
@Service
public class AidProgramService {

	private final AidProgramRepository AidProgramRepository;
	private final OrganizerRepository OrganizerRepository;

    @Autowired
    public AidProgramService(AidProgramRepository AidProgramRepository, OrganizerRepository OrganizerRepository) {
        this.AidProgramRepository = AidProgramRepository;
        this.OrganizerRepository = OrganizerRepository;
    }

    public List<AidProgram> getAllAidPrograms() {
        return AidProgramRepository.findAll();
    }

    public AidProgram getAidProgramById(long id) {   	
        return AidProgramRepository.findById(id).orElse(null);
           
    }
    
    public List<AidProgram> getAidProgramsByStatus(String status) {   	
        return AidProgramRepository.getAidProgramsByStatus(status);
        
        
    }

    public AidProgram createAidProgram(AidProgram aidProgram) {
        try {
            if (AidProgramRepository.existsByName(aidProgram.getName())) {
                // Duplicate AidProgram found
                return null;
            } else {
                // No duplicate AidProgram found, check if organizer exists
                if (OrganizerRepository.existsById(aidProgram.getOrganizer().getOrganizerId())) {
                    return AidProgramRepository.save(aidProgram);
                } else {
                    // Organizer doesn't exist
                    Organizer savedOrganizer = OrganizerRepository.save(aidProgram.getOrganizer());
                    // Set the saved Organizer to the AidProgram
                    aidProgram.setOrganizer(savedOrganizer);
                    return AidProgramRepository.save(aidProgram);
                }
            }
        } catch (DataIntegrityViolationException e) {
            // Log details of the exception
            throw new DuplicateKeyException("Duplicate AidProgram creation attempted", e);
        } catch (Exception e) {
            // Log details of the exception
            throw new RuntimeException("Internal Server Error", e);
        }
    }

    public AidProgram updateAidProgram(long id, AidProgram updatedAidProgram) {
        AidProgram existingAidProgram = AidProgramRepository.findById(id).orElse(null);

        if (existingAidProgram != null) {
            // AidProgram found, proceed with the update
            existingAidProgram.setName(updatedAidProgram.getName());
            existingAidProgram.setDescription(updatedAidProgram.getDescription());
            existingAidProgram.setLocation(updatedAidProgram.getLocation());
            existingAidProgram.setStatus(updatedAidProgram.getStatus());

            return AidProgramRepository.save(existingAidProgram);
        } else {
            // AidProgram not found, handle the situation accordingly
            return null; 
        }
    }

    public boolean deleteAidProgram(Long id) {
    	AidProgram existingAidProgram = AidProgramRepository.findById(id).orElse(null);

        if (existingAidProgram != null) {
            // AidProgram found, proceed with the deletion
            AidProgramRepository.delete(existingAidProgram);
            return true;
        } else {
            // AidProgram not found, return false
            return false;
        }

    }
	
}
