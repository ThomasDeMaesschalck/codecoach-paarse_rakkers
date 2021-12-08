package com.switchfully.codecoach.service;
import com.switchfully.codecoach.api.dto.UserDTO;
import com.switchfully.codecoach.api.mappers.CoachInfoMapper;
import com.switchfully.codecoach.api.mappers.UserMapper;
import com.switchfully.codecoach.domain.User;
import com.switchfully.codecoach.exception.UserNotFoundException;
import com.switchfully.codecoach.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final CoachInfoMapper coachInfoMapper;

    @Autowired
    public UserService(UserMapper userMapper, UserRepository userRepository, CoachInfoMapper coachInfoMapper) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.coachInfoMapper = coachInfoMapper;
    }

    public UserDTO registerUser(UserDTO dto) {
       return  userMapper.toDTO(userRepository.save(userMapper.toEntity(dto)));
    }


    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void assertUserExists(String userId) {
            if (!userRepository.existsById(UUID.fromString(userId)))
                throw new UserNotFoundException(userId);

    }

    public User getSpecificUserById(String userId) {
        assertUserExists(userId);
        return userRepository.findById(UUID.fromString(userId)).get();
    }
}
