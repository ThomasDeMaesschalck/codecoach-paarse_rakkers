package com.switchfully.codecoach.service;

import com.switchfully.codecoach.api.dto.UpdateUserDTO;
import com.switchfully.codecoach.api.dto.UserDTO;
import com.switchfully.codecoach.api.mappers.CoachInfoMapper;
import com.switchfully.codecoach.api.mappers.UserMapper;
import com.switchfully.codecoach.domain.Topic;
import com.switchfully.codecoach.domain.User;
import com.switchfully.codecoach.domain.UserRole;
import com.switchfully.codecoach.exception.EmailNotUniqueException;
import com.switchfully.codecoach.exception.UnauthorizedUserException;
import com.switchfully.codecoach.exception.UserNotFoundException;
import com.switchfully.codecoach.repository.SessionRepository;
import com.switchfully.codecoach.repository.UserRepository;
import com.switchfully.codecoach.repository.specifications.UserSpecification;
import com.switchfully.codecoach.security.authentication.jwt.JwtGenerator;
import com.switchfully.codecoach.security.authentication.user.api.Account;
import com.switchfully.codecoach.security.authentication.user.api.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    private final SessionRepository sessionRepository;
    private final CoachInfoMapper coachInfoMapper;
    private final UserSpecification userSpecification;
    private final JwtGenerator jwtGenerator;

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    private static final int EXPERIENCE_POINTS_MULTIPLIER = 10;

    @Autowired
    public UserService(UserMapper userMapper, UserRepository userRepository,
                       SessionRepository sessionRepository, CoachInfoMapper coachInfoMapper, UserSpecification userSpecification,
                       JwtGenerator jwtGenerator) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.coachInfoMapper = coachInfoMapper;
        this.userSpecification = userSpecification;
        this.jwtGenerator = jwtGenerator;
    }

    public UserDTO registerUser(UserDTO dto) {
        assertEmailIsNotADuplicate(dto.getEmail());

        User user = userMapper.toEntity(dto);
        user.setUserRole(UserRole.COACHEE);
        user.setCoachInfo(null);
        return userMapper.toDTO(userRepository.save(user));
    }

    public UserDTO updateUser(UUID userId, UpdateUserDTO dto, String token) {
        assertUserExists(userId.toString());
        var authorization = jwtGenerator.convertToken(token.replace("Bearer ", ""));
        assertLoggedInUserHasEditRights(userId, authorization);

        User userFromDB = userRepository.findById(userId).get();

        if (!userFromDB.getEmail().equals(dto.getEmail())) {
            assertEmailIsNotADuplicate(dto.getEmail());
            userFromDB.setEmail(dto.getEmail());

        }


        userFromDB.setFirstName(dto.getFirstName());
        userFromDB.setLastName(dto.getLastName());
        userFromDB.setCompanyTeam(dto.getCompanyTeam());
        userFromDB.setPictureURL(dto.getPictureURL());

        if (dto.getUserRole() == UserRole.COACH) {
            if (dto.getCoachInfo() != null) {
                userFromDB.setCoachInfo(coachInfoMapper.toEntity(dto.getCoachInfo()));
            }
            userFromDB.setUserRole(UserRole.COACH);
        }

        return userMapper.toDTO(userFromDB);
    }

    private void assertLoggedInUserHasEditRights(UUID userId, UsernamePasswordAuthenticationToken authorization) {
        if (!authorization.getAuthorities().contains(UserRole.ADMIN) && !doesAuthorizedUserMatchUserId(authorization, userId)) {
            System.out.println("HERE");
            throw new UnauthorizedUserException("User has no authorization to edit this");
        }
    }

    private boolean doesAuthorizedUserMatchUserId(UsernamePasswordAuthenticationToken authorization, UUID userId) {
        Optional<User> user = userRepository.findByEmail(authorization.getName());
        return user.isPresent() && user.get().getId().equals(userId);
    }


    public List<UserDTO> getAllUsers(Topic.TopicName topic, UserRole role, String partialSearch) {
        Specification<User> queryFilter = userSpecification.getUsers(topic, role, partialSearch);

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

    public UserDTO getUserDTO(String userId, String token) {
        var authorization = jwtGenerator.convertToken(token.replace("Bearer ", ""));
        if(!authorization.getAuthorities().contains(UserRole.ADMIN) && !doesAuthorizedUserMatchUserId(authorization, UUID.fromString(userId))) {
            throw new UnauthorizedUserException("Not authorized");
        }

        UserDTO userDTO = userMapper.toDTO(getSpecificUserById(userId));
        int xp = sessionRepository.countAllByCoachAndCoachFeedbackNotNullAndCoacheeFeedbackNotNull(getSpecificUserById(userId));
        userDTO.getCoachInfo().setXp(xp * EXPERIENCE_POINTS_MULTIPLIER);
        return userDTO;

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
