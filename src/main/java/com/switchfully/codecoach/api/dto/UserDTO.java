package com.switchfully.codecoach.api.dto;

import com.switchfully.codecoach.domain.UserRole;

import java.util.UUID;

public class UserDTO {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String pictureURL;
    private String companyTeam;
    private UserRole userRole;
    private CoachInfoDTO coachInfo;

}
