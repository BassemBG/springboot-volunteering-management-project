package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table()
public class Volunteer {

	
	@Id
    @GeneratedValue
    private Long id;

    private String name;
    private String contactEmail;
    private String contactPhone;

    public Volunteer() {
    }

    public Volunteer(Long id) {
        this.id = id;
    }
    
    
    public Volunteer(Long id, String name, String contactEmail, String contactPhone) {
        this.id = id;
    	this.name = name;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
    }
    
    public Volunteer(String name, String contactEmail, String contactPhone) {
        this.name = name;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
    
    
}
