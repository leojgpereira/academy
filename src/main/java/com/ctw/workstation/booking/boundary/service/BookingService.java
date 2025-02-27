package com.ctw.workstation.booking.boundary.service;

import com.ctw.workstation.booking.entity.Booking;
import com.ctw.workstation.booking.entity.dto.request.CreateBookingRequest;
import com.ctw.workstation.booking.entity.dto.request.UpdateBookingRequest;
import com.ctw.workstation.booking.entity.repository.BookingRepository;
import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.rack.entity.enums.RackStatus;
import com.ctw.workstation.rack.entity.repository.RackRepository;
import com.ctw.workstation.teammember.entity.TeamMember;
import com.ctw.workstation.teammember.entity.repository.TeamMemberRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class BookingService {
    @Inject
    RackRepository rackRepository;
    @Inject
    TeamMemberRepository teamMemberRepository;
    @Inject
    BookingRepository bookingRepository;

    @Transactional
    public Booking addBooking(CreateBookingRequest createBookingRequest) {
        Rack rack = rackRepository.findById(createBookingRequest.rackId());
        TeamMember requester = teamMemberRepository.findById(createBookingRequest.requesterId());

        if(isValidBooking(rack, requester, createBookingRequest)) {
            Booking booking = new Booking(createBookingRequest, rack, requester);

            rack.updateStatus(RackStatus.BOOKED);

            bookingRepository.persist(booking);

            return booking;
        }

        throw new BadRequestException();
    }

    private boolean isValidBooking(Rack rack, TeamMember requester, CreateBookingRequest createBookingRequest) {
        return rack != null && requester != null && rack.getStatus() != RackStatus.UNAVAILABLE && !isRackAlreadyBooked(rack, createBookingRequest.bookFrom(), createBookingRequest.bookTo()) && rack.getTeam() == requester.getTeam();
    }

    private boolean isRackAlreadyBooked(Rack rack, LocalDateTime bookFrom, LocalDateTime bookTo) {
        List<Booking> rackBookings = bookingRepository.findByRack(rack);

        boolean bookedOnStartDate = rackBookings.stream().anyMatch(booking -> bookFrom.isAfter(booking.getBookFrom()) && bookFrom.isBefore(booking.getBookTo()));

        boolean bookedOnEndDate = rackBookings.stream().anyMatch(booking -> bookTo.isAfter(booking.getBookFrom()) && bookTo.isBefore(booking.getBookTo()));

        return bookedOnStartDate || bookedOnEndDate;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.listAll();
    }

    public Booking getBookingById(UUID id) {
        return bookingRepository.findById(id);
    }

    @Transactional
    public void updateBooking(UUID id, UpdateBookingRequest updateBookingRequest) {
        Booking booking = bookingRepository.findById(id);

        booking.update(updateBookingRequest);
    }

    @Transactional
    public void removeBooking(UUID id) {
        bookingRepository.deleteById(id);
    }
}
