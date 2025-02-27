package com.ctw.workstation.rack.entity.dto.request;

import com.ctw.workstation.rack.entity.enums.RackStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdateRackRequest(
        @NotBlank(message = "Rack serial number may not be blank")
        String serialNumber,
        @NotNull(message = "Rack status should be valid")
        RackStatus status,
        @NotBlank(message = "Rack default location may not be blank")
        String defaultLocation,
        UUID teamId
) {
}
