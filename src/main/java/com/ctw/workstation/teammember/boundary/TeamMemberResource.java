package com.ctw.workstation.teammember.boundary;

import com.ctw.workstation.teammember.boundary.service.TeamMemberService;
import com.ctw.workstation.teammember.entity.TeamMember;
import com.ctw.workstation.teammember.entity.dto.request.CreateTeamMemberRequest;
import com.ctw.workstation.teammember.entity.dto.request.UpdateTeamMemberRequest;
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

@Path("/team-members")
public class TeamMemberResource {
    @Inject
    TeamMemberService teamMemberService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTeamMember(@RequestBody @Valid CreateTeamMemberRequest createTeamMemberRequest) {
        TeamMember teamMember = teamMemberService.addTeamMember(createTeamMemberRequest);

        return Response.status(Response.Status.CREATED).entity(teamMember).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllTeamMembers() {
        Map<String, List<TeamMember>> teamMembers = new HashMap<>();

        teamMembers.put("teamMembers", teamMemberService.getAllTeamMembers());

        return Response.ok(teamMembers).build();
    }

    @GET
    @Path("{id}")
    public Response retrieveTeamMemberById(@PathParam("id") UUID id) {
        TeamMember teamMember = teamMemberService.getTeamMemberById(id);

        return Response.ok(teamMember).build();
    }

    @PUT
    @Path("{id}")
    public Response updateTeamMember(@PathParam("id") UUID id, @RequestBody @Valid UpdateTeamMemberRequest updateTeamMemberRequest) {
        teamMemberService.updateTeamMember(id, updateTeamMemberRequest);

        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("{id}")
    public Response removeTeamMember(@PathParam("id") UUID id) {
        teamMemberService.removeTeamMember(id);

        return Response.status(Response.Status.OK).build();
    }
}
