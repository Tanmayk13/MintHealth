package com.dev.minthealth.dto;

import com.dev.minthealth.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminCreateUserResponse {

    @Schema(example = "1")
    private Long userId;

    @Schema(example = "10")
    private Long doctorId;

    @Schema(example = "jane.doe@minthealth.com")
    private String email;

    @Schema(example = "DOCTOR")
    private Role role;
}
