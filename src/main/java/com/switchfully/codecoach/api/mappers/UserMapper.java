package com.switchfully.codecoach.api.mappers;

import com.switchfully.codecoach.api.dto.UserDTO;
import com.switchfully.codecoach.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final CoachInfoMapper coachInfoMapper;

    @Autowired
    public UserMapper(CoachInfoMapper coachInfoMapper) {
        this.coachInfoMapper = coachInfoMapper;
    }

    public User toEntity(UserDTO userDTO){
        return User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .companyTeam(userDTO.getCompanyTeam())
                .email(userDTO.getEmail())
                .userRole(userDTO.getUserRole())
                .pictureURL(userDTO.getPictureURL())
                .password(userDTO.getPassword())
                .coachInfo(coachInfoMapper.toEntity(userDTO.getCoachInfo()))
                .build();
    }

    public UserDTO toDTO(User user){
        return UserDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .id(user.getId())
                .email(user.getEmail())
                .pictureURL(user.getPictureURL())
                .companyTeam(user.getCompanyTeam())
                .userRole(user.getUserRole())
                .coachInfo(coachInfoMapper.toDTO(user.getCoachInfo()))
                .build();
    }

}
