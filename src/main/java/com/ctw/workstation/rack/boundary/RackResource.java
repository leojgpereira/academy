package com.ctw.workstation.rack.boundary;

import com.ctw.workstation.rack.boundary.service.RackService;
import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.rack.entity.dto.request.CreateRackRequest;
import com.ctw.workstation.rack.entity.dto.request.UpdateRackRequest;
import com.ctw.workstation.rack.entity.enums.RackStatus;
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

@Path("/racks")
public class RackResource {
    @Inject
    RackService rackService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRack(@RequestBody @Valid CreateRackRequest createRackRequest) {
        Rack rack = rackService.addRack(createRackRequest);

        return Response.status(Response.Status.CREATED).entity(rack).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllRacks() {
        Map<String, List<Rack>> racks = new HashMap<>();

        racks.put("racks", rackService.getAllRacks());

        return Response.ok(racks).build();
    }

    @GET
    @Path("racks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveRacksByStatus(@QueryParam("status") RackStatus rackStatus) {
        Map<String, List<Rack>> racks = new HashMap<>();

        racks.put("racks", rackService.getRacksByStatus(rackStatus));

        return Response.ok(racks).build();
    }

    @GET
    @Path("{id}")
    public Response retrieveRackById(@PathParam("id") UUID id) {
        Rack rack = rackService.getRackById(id);

        return Response.ok(rack).build();
    }

    @PUT
    @Path("{id}")
    public Response updateRack(@PathParam("id") UUID id, @RequestBody @Valid UpdateRackRequest updateRackRequest) {
        rackService.updateRack(id, updateRackRequest);

        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("{id}")
    public Response removeRack(@PathParam("id") UUID id) {
        rackService.removeRack(id);

        return Response.status(Response.Status.OK).build();
    }
}
