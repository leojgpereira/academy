package com.ctw.workstation.team.boundary;

import com.ctw.workstation.team.boundary.service.TeamService;
import com.ctw.workstation.team.entity.Team;
import com.ctw.workstation.team.entity.dto.request.CreateTeamRequest;
import com.ctw.workstation.team.entity.dto.request.UpdateTeamRequest;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Path("/teams")
public class TeamResource {
    @Inject
    TeamService teamService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTeam(@RequestBody @Valid CreateTeamRequest createTeamRequest) {
        Team team = teamService.addTeam(createTeamRequest);

        return Response.status(Response.Status.CREATED).entity(team).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllTeams() {
        Map<String, List<Team>> teams = new HashMap<>();

        teams.put("teams", teamService.getAllTeams());

        return Response.ok(teams).build();
    }

    @GET
    @Path("{id}")
    public Response retrieveTeamById(@PathParam("id") UUID id) {
        Team team = teamService.getTeamById(id);

        return Response.ok(team).build();
    }

    @PUT
    @Path("{id}")
    public Response updateTeam(@PathParam("id") UUID id, @RequestBody @Valid UpdateTeamRequest updateTeamRequest) {
        teamService.updateTeam(id, updateTeamRequest);

        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("{id}")
    public Response removeTeam(@PathParam("id") UUID id) {
        teamService.removeTeam(id);

        return Response.status(Response.Status.OK).build();
    }
}
