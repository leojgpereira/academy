package com.ctw.workstation.rackasset.boundary.service;

import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.rack.entity.repository.RackRepository;
import com.ctw.workstation.rackasset.entity.RackAsset;
import com.ctw.workstation.rackasset.entity.dto.request.CreateRackAssetRequest;
import com.ctw.workstation.rackasset.entity.dto.request.UpdateRackAssetRequest;
import com.ctw.workstation.rackasset.entity.repository.RackAssetRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class RackAssetService {
    @Inject
    RackRepository rackRepository;
    @Inject
    RackAssetRepository rackAssetRepository;

    @Transactional
    public RackAsset addRackAsset(CreateRackAssetRequest createRackAssetRequest) {
        Rack rack = rackRepository.findById(createRackAssetRequest.rackId());

        if (rack == null) {
            throw new BadRequestException();
        }

        RackAsset rackAsset = new RackAsset(createRackAssetRequest, rack);

        rackAssetRepository.persist(rackAsset);

        return rackAsset;
    }

    public List<RackAsset> getAllRackAssets() {
        return rackAssetRepository.listAll();
    }

    public RackAsset getRackAssetById(UUID id) {
        RackAsset rackAsset = rackAssetRepository.findById(id);

        if (rackAsset == null) {
            throw new NotFoundException();
        }

        return rackAsset;
    }

    @Transactional
    public void updateRackAsset(UUID id, UpdateRackAssetRequest updateRackAssetRequest) {
        Rack rack = rackRepository.findById(updateRackAssetRequest.rackId());

        if (rack == null) {
            throw new BadRequestException();
        }

        RackAsset rackAsset = rackAssetRepository.findById(id);

        if (rackAsset == null) {
            throw new NotFoundException();
        }

        rackAsset.update(updateRackAssetRequest, rack);
    }

    @Transactional
    public void removeRackAsset(UUID id) {
        RackAsset rackAsset = rackAssetRepository.findById(id);

        if (rackAsset == null) {
            throw new NotFoundException();
        }

        rackAssetRepository.deleteById(id);
    }
}
