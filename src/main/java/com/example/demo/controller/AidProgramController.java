package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AidProgram;
import com.example.demo.service.AidProgramService;

@RestController
@RequestMapping("/RestApi/AideHumanitaire/aidPrograms")
public class AidProgramController {

	private final AidProgramService AidProgramService;

    @Autowired
    public AidProgramController(AidProgramService AidProgramService) {
        this.AidProgramService = AidProgramService;
    }

    @GetMapping
    public ResponseEntity<List<AidProgram>> getAllAidPrograms() {
    	try {
            return ResponseEntity.status(HttpStatus.OK).body(AidProgramService.getAllAidPrograms());
    	}catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    }

    @GetMapping("/{id}")
    public ResponseEntity<AidProgram> getAidProgramById(@PathVariable Long id) {
    	try {
    		AidProgram AidProgram = AidProgramService.getAidProgramById(id);

            if(AidProgram != null) {
            	return ResponseEntity.status(HttpStatus.OK).body(AidProgram);
            }else {
            	return ResponseEntity.status(HttpStatus.NO_CONTENT).body(AidProgram);

            }
    	}catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
        
    }
    
    
    @GetMapping("/filter")
    public ResponseEntity<List<AidProgram>> getAidProgramsByStatus(@RequestParam("status") String status) {
    	try {
    		List<AidProgram> AidPrograms = AidProgramService.getAidProgramsByStatus(status);

            if(!AidPrograms.isEmpty()) {
            	return ResponseEntity.status(HttpStatus.OK).body(AidPrograms);
            }else {
            	return ResponseEntity.status(HttpStatus.NO_CONTENT).body(AidPrograms);

            }
    	}catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
        
    }
    

    @PostMapping
    public ResponseEntity<Object> createAidProgram(@RequestBody AidProgram aidProgram) {
        try {
            AidProgram createdAidProgram = AidProgramService.createAidProgram(aidProgram);

            if (createdAidProgram != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(createdAidProgram);
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate AidProgram found");
            }
        } catch (DuplicateKeyException e) {
            // Log details of the exception
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate AidProgram found");
        } catch (Exception e) {
            // Log details of the exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AidProgram> updateAidProgram(@PathVariable Long id, @RequestBody AidProgram updatedAidProgram) {
        
    	try {
    		AidProgram updatedEntity = AidProgramService.updateAidProgram(id, updatedAidProgram);
            
            return updatedEntity != null ? ResponseEntity.ok(updatedEntity) : ResponseEntity.notFound().build();
        
    	}catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    	}
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAidProgram(@PathVariable Long id) {
    	try {
    	    boolean deleted = AidProgramService.deleteAidProgram(id);
            return deleted ? ResponseEntity.ok("Deleted Successfully !") : ResponseEntity.notFound().build();		
    	}catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    }
	
}
