package com.example.demo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Organizer {

	@Id
    @GeneratedValue
    private long organizerId;

    private String name;
    private String contactEmail;
    private String contactPhone;

    // Constructors, getters, and setters

    public Organizer() {
    }

    public Organizer(long organizerId) {
        this.organizerId = organizerId;
    }
    
    public Organizer(long organizerId, String name, String contactEmail, String contactPhone) {
        this.organizerId = organizerId;
    	this.name = name;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
    }
    
    public Organizer(String name, String contactEmail, String contactPhone) {
        this.name = name;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
    }

    // Getters and setters

    public long getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(long organizerId) {
        this.organizerId = organizerId;
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
