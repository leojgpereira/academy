package com.ctw.workstation.rackasset.entity;

import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.rackasset.entity.dto.request.CreateRackAssetRequest;
import com.ctw.workstation.rackasset.entity.dto.request.UpdateRackAssetRequest;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "t_rack_asset")
public class RackAsset {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "asset_tag")
    private String assetTag;
    @ManyToOne(fetch = FetchType.EAGER)
    private Rack rack;

    public RackAsset() {}

    public RackAsset(CreateRackAssetRequest createRackAssetRequest, Rack rack) {
        setAssetTag(createRackAssetRequest.assetTag());
        setRack(rack);
    }

    public void update(UpdateRackAssetRequest updateRackAssetRequest, Rack rack) {
        setAssetTag(updateRackAssetRequest.assetTag());
        setRack(rack);
    }

    private void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    private void setRack(Rack rack) {
        this.rack = rack;
    }

    public UUID getId() {
        return id;
    }

    public String getAssetTag() {
        return assetTag;
    }

    public Rack getRack() {
        return rack;
    }
}
