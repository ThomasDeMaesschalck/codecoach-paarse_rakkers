package com.switchfully.codecoach.api.dto;

import com.switchfully.codecoach.domain.UserRole;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.UUID;

@Builder
@Data
public class UserDTO {


    private UUID id;
    @NotBlank(message = "First name cannot be blank") @NotNull(message = "First name cannot be null")
    private String firstName;
    @NotBlank(message = "Last name cannot be blank") @NotNull(message = "Last name cannot be null")
    private String lastName;
    @Email(message ="Email has to be valid")
    private String email;
    @NotBlank(message = "Password name cannot be blank") @NotNull(message = "Password name cannot be null")
    @Size(min = 8, message="Password has to be longer than 8 characters")
    private String password;

    private String pictureURL;
    private String companyTeam;
    private UserRole userRole;
    @Valid
    private CoachInfoDTO coachInfo;

}
