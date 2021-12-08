package com.switchfully.codecoach.service;

import com.switchfully.codecoach.api.mappers.UserMapper;
import com.switchfully.codecoach.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;


    @Autowired
    public UserService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
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
}
