package com.ctw.workstation.booking.entity;

import com.ctw.workstation.booking.entity.dto.request.CreateBookingRequest;
import com.ctw.workstation.booking.entity.dto.request.UpdateBookingRequest;
import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.teammember.entity.TeamMember;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "t_booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "book_from")
    private LocalDateTime bookFrom;
    @Column(name = "book_to")
    private LocalDateTime bookTo;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;
    @ManyToOne(fetch = FetchType.EAGER)
    private Rack rack;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "requester_id")
    private TeamMember teamMember;

    public Booking() {}

    public Booking(CreateBookingRequest createBookingRequest, Rack rack, TeamMember requester) {
        setBookFrom(createBookingRequest.bookFrom());
        setBookTo(createBookingRequest.bookTo());
        setTeamMember(requester);
        setRack(rack);
    }

    public void update(UpdateBookingRequest updateBookingRequest) {
        setBookFrom(updateBookingRequest.bookFrom());
        setBookTo(updateBookingRequest.bookTo());
    }

    private void setBookFrom(LocalDateTime bookFrom) {
        this.bookFrom = bookFrom;
    }

    private void setBookTo(LocalDateTime bookTo) {
        this.bookTo = bookTo;
    }

    private void setRack(Rack rack) {
        this.rack = rack;
    }

    private void setTeamMember(TeamMember teamMember) {
        this.teamMember = teamMember;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getBookFrom() {
        return bookFrom;
    }

    public LocalDateTime getBookTo() {
        return bookTo;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public Rack getRack() {
        return rack;
    }

    public TeamMember getTeamMember() {
        return teamMember;
    }
}
