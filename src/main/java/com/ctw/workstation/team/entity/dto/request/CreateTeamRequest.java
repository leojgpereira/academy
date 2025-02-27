package com.ctw.workstation.team.entity.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateTeamRequest(
        @NotBlank(message = "Team name may not be blank")
        String name,
        @NotBlank(message = "Team product name may not be blank")
        String product,
        @NotBlank(message = "Team default location may not be blank")
        String defaultLocation
) {
}
