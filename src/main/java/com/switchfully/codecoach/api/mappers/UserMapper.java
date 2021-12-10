package com.switchfully.codecoach.api.mappers;

import com.switchfully.codecoach.api.dto.UserDTO;
import com.switchfully.codecoach.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final CoachInfoMapper coachInfoMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserMapper(CoachInfoMapper coachInfoMapper) {
        this.coachInfoMapper = coachInfoMapper;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User toEntity(UserDTO userDTO){
        return User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .companyTeam(userDTO.getCompanyTeam())
                .email(userDTO.getEmail())
                .userRole(userDTO.getUserRole())
                .pictureURL(userDTO.getPictureURL())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .coachInfo(userDTO.getCoachInfo() == null ? null : coachInfoMapper.toEntity(userDTO.getCoachInfo()))
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
                .coachInfo(user.getCoachInfo() == null ? null : coachInfoMapper.toDTO(user.getCoachInfo()))
                .build();
    }

}
