package com.example.demo.model;
import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class EmbeddedEnrollmentId implements Serializable {

    @Column(name = "organizer_id")
    private Long organizerId;

    @Column(name = "volunteer_id")
    private Long volunteerId;

    // Constructors

    public EmbeddedEnrollmentId() {
    }

    public EmbeddedEnrollmentId(Long organizerId, Long volunteerId) {
        this.organizerId = organizerId;
        this.volunteerId = volunteerId;
    }

    // Getters and setters

    public Long getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(Long organizerId) {
        this.organizerId = organizerId;
    }

    public Long getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(Long volunteerId) {
        this.volunteerId = volunteerId;
    }

    // Implement equals and hashCode methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmbeddedEnrollmentId that = (EmbeddedEnrollmentId) o;
        return Objects.equals(organizerId, that.organizerId) &&
               Objects.equals(volunteerId, that.volunteerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizerId, volunteerId);
    }
}