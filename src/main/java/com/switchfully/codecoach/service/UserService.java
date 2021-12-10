package com.switchfully.codecoach.service;

import com.switchfully.codecoach.api.dto.UserDTO;
import com.switchfully.codecoach.api.mappers.CoachInfoMapper;
import com.switchfully.codecoach.api.mappers.UserMapper;
import com.switchfully.codecoach.domain.Topic;
import com.switchfully.codecoach.domain.User;
import com.switchfully.codecoach.domain.UserRole;
import com.switchfully.codecoach.exception.EmailNotUniqueException;
import com.switchfully.codecoach.exception.UnauthorizedUserException;
import com.switchfully.codecoach.exception.UserNotFoundException;
import com.switchfully.codecoach.repository.UserRepository;
import com.switchfully.codecoach.repository.specifications.UserSpecification;
import com.switchfully.codecoach.security.authentication.user.api.Account;
import com.switchfully.codecoach.security.authentication.user.api.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService implements AccountService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final CoachInfoMapper coachInfoMapper;
    private final UserSpecification userSpecification;

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserMapper userMapper, UserRepository userRepository,
                       CoachInfoMapper coachInfoMapper, UserSpecification userSpecification) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.coachInfoMapper = coachInfoMapper;
        this.userSpecification = userSpecification;
    }

    public UserDTO registerUser(UserDTO dto) {
        assertEmailIsNotADuplicate(dto.getEmail());

        User user = userMapper.toEntity(dto);
        user.setUserRole(UserRole.COACHEE);
        user.setCoachInfo(null);
        return userMapper.toDTO(userRepository.save(user));
    }

    public UserDTO updateUser(UUID userId, UserDTO dto) {
        assertUserExists(userId.toString());

        User userFromDB = userRepository.findById(userId).get();

        if (!userFromDB.getEmail().equals(dto.getEmail())) {
            assertEmailIsNotADuplicate(dto.getEmail());
        }

        userFromDB.setFirstName(dto.getFirstName());
        userFromDB.setLastName(dto.getLastName());
        userFromDB.setCompanyTeam(dto.getCompanyTeam());
        userFromDB.setEmail(dto.getEmail());
        userFromDB.setPictureURL(dto.getPictureURL());

        if (dto.getCoachInfo() != null) {
            userFromDB.setCoachInfo(coachInfoMapper.toEntity(dto.getCoachInfo()));
            userFromDB.setUserRole(UserRole.COACH);
        }

        return userMapper.toDTO(userRepository.save(userFromDB));

    }

    public List<UserDTO> getAllUsers(Topic.TopicName topic, UserRole role) {
        Specification<User> queryFilter = userSpecification.getUsers(topic, role);

        return userRepository.findAll(queryFilter)
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void assertUserExists(String userId) {
        if (!userRepository.existsById(UUID.fromString(userId))) {
            logger.error("User with id " + userId + "not found");
            throw new UserNotFoundException(userId);
        }
    }

    public UserDTO getUserDTO(String userId) {
        return userMapper.toDTO(getSpecificUserById(userId));
    }

    public User getSpecificUserById(String userId) {
        assertUserExists(userId);
        return userRepository.findById(UUID.fromString(userId)).get();
    }

    public void assertEmailIsNotADuplicate(String email) {
        if (userRepository.existsByEmail(email)) {
            logger.error("User with email " + email + " already exists");
            throw new EmailNotUniqueException(email);
        }
    }

    public void assertUserHasRoles(User user, UserRole... userRole) {
        List<UserRole> roles = Arrays.asList(userRole);

        if (!roles.contains(user.getUserRole())) {
            logger.error("User with id " + user.getId() + "does not have role: " + userRole);
            throw new UnauthorizedUserException(userRole);
        }
    }

    @Override
    public Optional<? extends Account> findByEmail(String userName) {
        return userRepository.findByEmail(userName);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
