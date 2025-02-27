package com.ctw.workstation.booking.entity.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UpdateBookingRequest(
        @NotNull(message = "Booking from may not be null")
        @FutureOrPresent(message = "Booking from should be a valid date and time")
        LocalDateTime bookFrom,
        @NotNull(message = "Booking to may not be null")
        @Future(message = "Booking from should be a valid date and time")
        LocalDateTime bookTo
) {
}
