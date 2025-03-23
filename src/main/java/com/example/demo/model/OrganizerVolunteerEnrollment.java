package com.example.demo.model;

import java.sql.Timestamp;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table
public class OrganizerVolunteerEnrollment {

	@EmbeddedId
    private EmbeddedEnrollmentId id;

    @ManyToOne
    @MapsId("organizerId")
    @JoinColumn(name = "organizer_id")
    private Organizer organizer;

    @ManyToOne
    @MapsId("volunteerId")
    @JoinColumn(name = "volunteer_id")
    private Volunteer volunteer;

    private Timestamp enrollmentDate;
    private Integer hoursContributed;
    private Integer role;
    private String status;

    public OrganizerVolunteerEnrollment() {
    }

    public OrganizerVolunteerEnrollment(Organizer organizer, Volunteer volunteer, Integer hoursContributed, Integer role, String status) {
        this.organizer = organizer;
        this.volunteer = volunteer;
        this.hoursContributed = hoursContributed;
        this.role = role;
        this.status = status;
        this.id = new EmbeddedEnrollmentId(organizer.getOrganizerId(), volunteer.getId());
    }

    // Getters and setters
    public EmbeddedEnrollmentId getId() {
        return id;
    }

    public void setId(EmbeddedEnrollmentId id) {
        this.id = id;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public Timestamp getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Timestamp enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Integer getHoursContributed() {
        return hoursContributed;
    }

    public void setHoursContributed(Integer hoursContributed) {
        this.hoursContributed = hoursContributed;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
	
}
