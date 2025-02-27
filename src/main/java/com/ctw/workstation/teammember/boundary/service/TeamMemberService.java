package com.ctw.workstation.teammember.boundary.service;

import com.ctw.workstation.team.entity.Team;
import com.ctw.workstation.team.entity.repository.TeamRepository;
import com.ctw.workstation.teammember.entity.TeamMember;
import com.ctw.workstation.teammember.entity.dto.request.CreateTeamMemberRequest;
import com.ctw.workstation.teammember.entity.dto.request.UpdateTeamMemberRequest;
import com.ctw.workstation.teammember.entity.repository.TeamMemberRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class TeamMemberService {
    @Inject
    TeamRepository teamRepository;
    @Inject
    TeamMemberRepository teamMemberRepository;

    @Transactional
    public TeamMember addTeamMember(CreateTeamMemberRequest createTeamMemberRequest) {
        Team team = null;

        if(createTeamMemberRequest.teamId() != null) {
            team = teamRepository.findById(createTeamMemberRequest.teamId());
        }

        TeamMember teamMember = new TeamMember(createTeamMemberRequest, team);

        teamMemberRepository.persist(teamMember);

        return teamMember;
    }

    public List<TeamMember> getAllTeamMembers() {
        return teamMemberRepository.listAll();
    }

    public TeamMember getTeamMemberById(UUID id) {
        TeamMember teamMember = teamMemberRepository.findById(id);

        if(teamMember == null) {
            throw new NotFoundException();
        }

        return teamMember;
    }

    @Transactional
    public void updateTeamMember(UUID id, UpdateTeamMemberRequest updateTeamMemberRequest) {
        Team team = null;

        if(updateTeamMemberRequest.teamId() != null) {
            team = teamRepository.findById(updateTeamMemberRequest.teamId());
        }

        TeamMember teamMember = teamMemberRepository.findById(id);

        if(teamMember == null) {
            throw new NotFoundException();
        }

        teamMember.update(updateTeamMemberRequest, team);
    }

    @Transactional
    public void removeTeamMember(UUID id) {
        TeamMember teamMember = teamMemberRepository.findById(id);

        if(teamMember == null) {
            throw new NotFoundException();
        }

        teamMemberRepository.deleteById(id);
    }
}
