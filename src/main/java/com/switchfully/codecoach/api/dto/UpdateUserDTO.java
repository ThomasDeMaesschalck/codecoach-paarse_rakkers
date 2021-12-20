package com.switchfully.codecoach.api.dto;


import com.switchfully.codecoach.domain.UserRole;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Builder
@Data
public class UpdateUserDTO {

    private String firstName;
    @NotBlank(message = "Last name cannot be blank") @NotNull(message = "Last name cannot be null")
    private String lastName;
    @Email(message ="Email has to be valid")
    private String email;
    private String pictureURL;
    private String companyTeam;
    private UserRole userRole;
    private String phoneNumber;
    @Valid
    private CoachInfoDTO coachInfo;

}