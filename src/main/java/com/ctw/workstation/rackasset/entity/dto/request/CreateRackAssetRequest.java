package com.ctw.workstation.rackasset.entity.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateRackAssetRequest(
        @NotBlank(message = "Rack asset tag may not be blank")
        String assetTag,
        @NotNull(message = "Rack should be valid")
        UUID rackId
) {
}
