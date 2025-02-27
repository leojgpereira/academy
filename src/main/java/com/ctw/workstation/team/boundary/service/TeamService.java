package com.ctw.workstation.team.boundary.service;

import com.ctw.workstation.team.entity.Team;
import com.ctw.workstation.team.entity.dto.request.CreateTeamRequest;
import com.ctw.workstation.team.entity.dto.request.UpdateTeamRequest;
import com.ctw.workstation.team.entity.repository.TeamRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class TeamService {
   @Inject
   TeamRepository teamRepository;

   @Transactional
   public Team addTeam(CreateTeamRequest createTeamRequest) {
       Team team = new Team(createTeamRequest);

       teamRepository.persist(team);

       return team;
   }

    public List<Team> getAllTeams() {
        return teamRepository.listAll();
    }

    public Team getTeamById(UUID id) {
        Team team = teamRepository.findById(id);

        if(team == null) {
            throw new NotFoundException();
        }

        return team;
    }

    @Transactional
    public void updateTeam(UUID id, UpdateTeamRequest updateTeamRequest) {
        Team team = teamRepository.findById(id);

        if(team == null) {
            throw new NotFoundException();
        }

        team.update(updateTeamRequest);
    }

    @Transactional
    public void removeTeam(UUID id) {
        Team team = teamRepository.findById(id);

        if(team == null) {
            throw new NotFoundException();
        }

        teamRepository.deleteById(id);
    }
}
