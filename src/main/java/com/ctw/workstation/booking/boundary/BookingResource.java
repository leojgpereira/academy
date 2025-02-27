package com.ctw.workstation.booking.boundary;

import com.ctw.workstation.booking.boundary.service.BookingService;
import com.ctw.workstation.booking.entity.Booking;
import com.ctw.workstation.booking.entity.dto.request.CreateBookingRequest;
import com.ctw.workstation.booking.entity.dto.request.UpdateBookingRequest;
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

@Path("/bookings")
public class BookingResource {
    @Inject
    BookingService bookingService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBooking(@RequestBody @Valid CreateBookingRequest createBookingRequest) {
        Booking booking = bookingService.addBooking(createBookingRequest);

        return Response.status(Response.Status.CREATED).entity(booking).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllBookings() {
        Map<String, List<Booking>> bookings = new HashMap<>();

        bookings.put("bookings", bookingService.getAllBookings());

        return Response.ok(bookings).build();
    }

    @GET
    @Path("{id}")
    public Response retrieveBookingById(@PathParam("id") UUID id) {
        Booking booking = bookingService.getBookingById(id);

        return Response.ok(booking).build();
    }

    @PUT
    @Path("{id}")
    public Response updateBooking(@PathParam("id") UUID id, @RequestBody @Valid UpdateBookingRequest updateBookingRequest) {
        bookingService.updateBooking(id, updateBookingRequest);

        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("{id}")
    public Response removeBooking(@PathParam("id") UUID id) {
        bookingService.removeBooking(id);

        return Response.status(Response.Status.OK).build();
    }
}
