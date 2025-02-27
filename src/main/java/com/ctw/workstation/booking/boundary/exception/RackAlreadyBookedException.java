package com.ctw.workstation.booking.boundary.exception;

public class RackAlreadyBookedException extends RuntimeException {
    public RackAlreadyBookedException() {
        super("Rack already booked!");
    }
}
