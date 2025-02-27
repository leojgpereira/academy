package com.ctw.workstation.booking.entity.repository;

import com.ctw.workstation.booking.entity.Booking;
import com.ctw.workstation.rack.entity.Rack;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class BookingRepository implements PanacheRepositoryBase<Booking, UUID> {
    public List<Booking> findByRack(Rack rack) {
        return find("rack", rack).list();
    }
}
