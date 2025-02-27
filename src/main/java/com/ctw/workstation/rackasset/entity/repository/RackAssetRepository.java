package com.ctw.workstation.rackasset.entity.repository;

import com.ctw.workstation.rackasset.entity.RackAsset;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class RackAssetRepository implements PanacheRepositoryBase<RackAsset, UUID> {

}
