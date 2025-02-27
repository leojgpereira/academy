package com.ctw.workstation.team.entity;

import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.team.entity.dto.request.CreateTeamRequest;
import com.ctw.workstation.team.entity.dto.request.UpdateTeamRequest;
import com.ctw.workstation.teammember.entity.TeamMember;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "t_team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String product;
    @Column(name = "default_location")
    private String defaultLocation;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;
    @OneToMany(mappedBy = "team")
    private List<TeamMember> teamMembers;
    @OneToMany(mappedBy = "team")
    private List<Rack> racks;

    public Team() {

    }

    public Team(CreateTeamRequest createTeamRequest) {
        setName(createTeamRequest.name());
        setProduct(createTeamRequest.product());
        setDefaultLocation(createTeamRequest.defaultLocation());
    }

    public void update(UpdateTeamRequest updateTeamRequest) {
        setName(updateTeamRequest.name());
        setProduct(updateTeamRequest.product());
        setDefaultLocation(updateTeamRequest.defaultLocation());
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setProduct(String product) {
        this.product = product;
    }

    private void setDefaultLocation(String defaultLocation) {
        this.defaultLocation = defaultLocation;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProduct() {
        return product;
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
}
