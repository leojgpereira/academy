package com.ctw.workstation.rack.boundary.service;

import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.rack.entity.dto.request.CreateRackRequest;
import com.ctw.workstation.rack.entity.dto.request.UpdateRackRequest;
import com.ctw.workstation.rack.entity.enums.RackStatus;
import com.ctw.workstation.rack.entity.repository.RackRepository;
import com.ctw.workstation.team.entity.Team;
import com.ctw.workstation.team.entity.repository.TeamRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class RackService {
    @Inject
    TeamRepository teamRepository;
    @Inject
    RackRepository rackRepository;

    @Transactional
    public Rack addRack(CreateRackRequest createRackRequest) {
        Team team = null;

        if(createRackRequest.teamId() != null) {
            team = teamRepository.findById(createRackRequest.teamId());
        }

        Rack rack = new Rack(createRackRequest, team);

        rackRepository.persist(rack);

        return rack;
    }

    public List<Rack> getAllRacks() {
        return rackRepository.listAll();
    }

    public List<Rack> getRacksByStatus(RackStatus rackStatus) {
        return rackRepository.findByStatus(rackStatus);
    }

    public Rack getRackById(UUID id) {
        Rack rack = rackRepository.findById(id);

        if(rack == null) {
            throw new NotFoundException();
        }

        return rack;
    }

    @Transactional
    public void updateRack(UUID id, UpdateRackRequest updateRackRequest) {
        Team team = null;

        if(updateRackRequest.teamId() != null) {
            team = teamRepository.findById(updateRackRequest.teamId());
        }

        Rack rack = rackRepository.findById(id);

        if(rack == null) {
            throw new NotFoundException();
        }

        rack.update(updateRackRequest, team);
    }

    @Transactional
    public void removeRack(UUID id) {
        Rack rack = rackRepository.findById(id);

        if(rack == null) {
            throw new NotFoundException();
        }

        rackRepository.deleteById(id);
    }
}
