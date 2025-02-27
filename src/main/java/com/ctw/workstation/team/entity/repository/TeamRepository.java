package com.ctw.workstation.team.entity.repository;

import com.ctw.workstation.team.entity.Team;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class TeamRepository implements PanacheRepositoryBase<Team, UUID> {

}
