package com.switchfully.codecoach.api.dto;

import com.switchfully.codecoach.domain.UserRole;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

@Builder
@Data
public class UserDTO {


    private UUID id;
    @NotBlank(message = "First name cannot be blank")
    @NotNull(message = "First name cannot be null")
    private String firstName;
    @NotBlank(message = "Last name cannot be blank")
    @NotNull(message = "Last name cannot be null")
    private String lastName;
    @Email(message = "Email has to be valid")
    private String email;
    @NotBlank(message = "Password cannot be blank")
    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password has to be longer than 8 characters")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$",
            message = "Password must have 8 characters, and contain at least one uppercase letter, one lowercase letter and one number.")
    private String password;

    private String pictureURL;
    private String companyTeam;
    @NotBlank(message = "Phone number cannot be blank")
    @NotNull(message = "Phone number name cannot be null")
    private String phoneNumber;

    private UserRole userRole;
    @Valid
    private CoachInfoDTO coachInfo;

}
