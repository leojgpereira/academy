package com.ctw.workstation.teammember.entity.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record CreateTeamMemberRequest(
        @NotBlank(message = "Team member CTW ID may not be blank")
        @Pattern(regexp = "CTW\\d\\d\\d\\d", message = " Team member CTW ID should be a valid format")
        String ctwId,
        @NotBlank(message = "Team member name may not be blank")
        String name,
        UUID teamId
){}
