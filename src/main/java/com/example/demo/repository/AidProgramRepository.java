package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.AidProgram;

public interface AidProgramRepository extends JpaRepository<AidProgram, Long> {

	boolean existsByName(String name);
	
	@Query("SELECT p FROM AidProgram p WHERE p.status = :status")
	List<AidProgram> getAidProgramsByStatus(@Param("status") String status);


}
