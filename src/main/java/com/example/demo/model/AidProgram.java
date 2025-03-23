package com.example.demo.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "aid_programs")
public class AidProgram {

	
	@Id
    @GeneratedValue
    private Long aidProgramId;
	
    private String name;
    private String description;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;
    
    private String location;
    private String status;
    private String aidCategory;

    @ManyToOne
    @JoinColumn(name = "organizer_id", nullable = false)
    private Organizer organizer;

 // Constructors

    public AidProgram() {
        // Default constructor
    }

    public AidProgram(String name, String description, Date startDate, Date endDate,
                      String location, String status, String aidCategory, Organizer organizer) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.status = status;
        this.aidCategory = aidCategory;
        this.organizer = organizer;
    }

    // Getters and Setters

    public Long getAidProgramId() {
        return aidProgramId;
    }

    public void setAidProgramId(Long aidProgramId) {
        this.aidProgramId = aidProgramId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAidCategory() {
        return aidCategory;
    }

    public void setAidCategory(String aidCategory) {
        this.aidCategory = aidCategory;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    
}
