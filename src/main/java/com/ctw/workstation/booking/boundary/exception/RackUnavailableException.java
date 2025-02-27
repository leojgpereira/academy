package com.ctw.workstation.booking.boundary.exception;

public class RackUnavailableException extends RuntimeException {
    public RackUnavailableException() {
        super("Rack unavailable");
    }
}
