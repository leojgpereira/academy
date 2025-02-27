package com.ctw.workstation.teammember.entity.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record UpdateTeamMemberRequest(
        @NotBlank(message = "Team member name may not be blank")
        String name,
        UUID teamId
) {
}
