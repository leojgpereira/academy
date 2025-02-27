package com.ctw.workstation.rack.entity;

import com.ctw.workstation.booking.entity.Booking;
import com.ctw.workstation.rack.entity.dto.request.CreateRackRequest;
import com.ctw.workstation.rack.entity.dto.request.UpdateRackRequest;
import com.ctw.workstation.rack.entity.enums.RackStatus;
import com.ctw.workstation.rackasset.entity.RackAsset;
import com.ctw.workstation.team.entity.Team;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "t_rack")
public class Rack {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "serial_number")
    private String serialNumber;
    @Enumerated(EnumType.STRING)
    private RackStatus status;
    @Column(name = "default_location")
    private String defaultLocation;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;
    @ManyToOne(fetch = FetchType.EAGER)
    private Team team;
    @OneToMany(mappedBy = "rack")
    private List<RackAsset> rackAssets;
    @OneToMany(mappedBy = "rack")
    private List<Booking> bookings;

    public Rack() {}

    public Rack(CreateRackRequest createRackRequest, Team team) {
        setSerialNumber(createRackRequest.serialNumber());
        setStatus(createRackRequest.status());
        setDefaultLocation(createRackRequest.defaultLocation());
        setTeam(team);
    }

    public void update(UpdateRackRequest updateRackRequest, Team team) {
        setSerialNumber(updateRackRequest.serialNumber());
        setStatus(updateRackRequest.status());
        setDefaultLocation(updateRackRequest.defaultLocation());
        setTeam(team);
    }

    public void updateStatus(RackStatus rackStatus) {
        setStatus(rackStatus);
    }

    private void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    private void setStatus(RackStatus status) {
        this.status = status;
    }

    private void setDefaultLocation(String defaultLocation) {
        this.defaultLocation = defaultLocation;
    }

    private void setTeam(Team team) {
        this.team = team;
    }

    public UUID getId() {
        return id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public RackStatus getStatus() {
        return status;
    }

    public String getDefaultLocation() {
        return defaultLocation;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public Team getTeam() {
        return team;
    }
}
