package com.ctw.workstation.rack.entity.repository;

import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.rack.entity.enums.RackStatus;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class RackRepository implements PanacheRepositoryBase<Rack, UUID> {
    public List<Rack> findByStatus(RackStatus rackStatus) {
        return find("status", rackStatus).stream().toList();
    }
}
