package com.ctw.workstation.rackasset.boundary;

import com.ctw.workstation.rackasset.boundary.service.RackAssetService;
import com.ctw.workstation.rackasset.entity.RackAsset;
import com.ctw.workstation.rackasset.entity.dto.request.CreateRackAssetRequest;
import com.ctw.workstation.rackasset.entity.dto.request.UpdateRackAssetRequest;
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

@Path("/rack-assets")
public class RackAssetResource {
    @Inject
    RackAssetService rackAssetService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRackAsset(@RequestBody @Valid CreateRackAssetRequest createRackAssetRequest) {
        RackAsset rackAsset = rackAssetService.addRackAsset(createRackAssetRequest);

        return Response.status(Response.Status.CREATED).entity(rackAsset).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllRackAssets() {
        Map<String, List<RackAsset>> rackAssets = new HashMap<>();

        rackAssets.put("rackAssets", rackAssetService.getAllRackAssets());

        return Response.ok(rackAssets).build();
    }

    @GET
    @Path("{id}")
    public Response retrieveRackAssetById(@PathParam("id") UUID id) {
        RackAsset rackAsset = rackAssetService.getRackAssetById(id);

        return Response.ok(rackAsset).build();
    }

    @PUT
    @Path("{id}")
    public Response updateRackAsset(@PathParam("id") UUID id, @RequestBody @Valid UpdateRackAssetRequest updateRackAssetRequest) {
        rackAssetService.updateRackAsset(id, updateRackAssetRequest);

        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("{id}")
    public Response removeRackAsset(@PathParam("id") UUID id) {
        rackAssetService.removeRackAsset(id);

        return Response.status(Response.Status.OK).build();
    }
}
