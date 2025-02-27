package com.ctw.workstation.team.entity.dto.response;

import java.util.UUID;

public record TeamResponse(
        UUID id,
        String name,
        String product,
        String defaultLocation
) {
}
