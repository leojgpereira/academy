package com.ctw.workstation.teammember.entity;

import com.ctw.workstation.booking.entity.Booking;
import com.ctw.workstation.team.entity.Team;
import com.ctw.workstation.teammember.entity.dto.request.CreateTeamMemberRequest;
import com.ctw.workstation.teammember.entity.dto.request.UpdateTeamMemberRequest;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "t_team_member")
public class TeamMember {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "ctw_id")
    private String ctwId;
    private String name;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;
    @ManyToOne(fetch = FetchType.EAGER)
    private Team team;
    @OneToMany(mappedBy = "teamMember")
    private List<Booking> bookings;

    public TeamMember() {}

    public TeamMember(CreateTeamMemberRequest createTeamMemberRequest, Team team) {
        setName(createTeamMemberRequest.name());
        setCtwId(createTeamMemberRequest.ctwId());
        setTeam(team);
    }

    public void update(UpdateTeamMemberRequest updateTeamMemberRequest, Team team) {
        setName(updateTeamMemberRequest.name());
        setTeam(team);
    }

    private void setCtwId(String ctwId) {
        this.ctwId = ctwId;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setTeam(Team team) {
        this.team = team;
    }

    public UUID getId() {
        return id;
    }

    public String getCtwId() {
        return ctwId;
    }

    public String getName() {
        return name;
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
