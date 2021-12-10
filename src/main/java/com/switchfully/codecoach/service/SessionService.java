package com.switchfully.codecoach.service;

import com.switchfully.codecoach.api.dto.SessionDTO;
import com.switchfully.codecoach.api.mappers.SessionMapper;
import com.switchfully.codecoach.domain.Session;
import com.switchfully.codecoach.domain.SessionStatus;
import com.switchfully.codecoach.domain.User;
import com.switchfully.codecoach.domain.UserRole;
import com.switchfully.codecoach.exception.SessionNotFoundException;
import com.switchfully.codecoach.repository.SessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class SessionService {

    private final SessionRepository sessionRepository;
    private final SessionMapper sessionMapper;
    private final UserService userService;

    private final Logger logger = LoggerFactory.getLogger(SessionService.class);


    @Autowired
    public SessionService(SessionRepository sessionRepository, SessionMapper sessionMapper, UserService userService) {
        this.sessionRepository = sessionRepository;
        this.sessionMapper = sessionMapper;
        this.userService = userService;
    }


    public SessionDTO saveSession(SessionDTO dto) {
        User coach = userService.getSpecificUserById(dto.getCoachId());
        User coachee = userService.getSpecificUserById(dto.getCoacheeId());
        return sessionMapper.toDTO(sessionRepository.save(sessionMapper.toEntity(dto, coach, coachee)));
    }

    public List<SessionDTO> getAllSessions(String coachId) {

        if (coachId != null) {
            User coach = userService.getSpecificUserById(coachId);
            userService.assertUserHasRoles(coach, UserRole.COACH, UserRole.ADMIN);
            return sessionRepository.findAllByCoach(coach).stream()
                    .map(sessionMapper::toDTO)
                    .collect(Collectors.toList());
        }

        else
            return sessionRepository.findAll().stream()
                    .map(sessionMapper::toDTO)
                    .collect(Collectors.toList());

    }

    public SessionDTO getSession(String sessionId) {
        assertSessionExists(sessionId);
        return sessionMapper.toDTO(sessionRepository.findById(UUID.fromString(sessionId)).get());
    }

    public void assertSessionExists(String sessionId) {
        if (!sessionRepository.existsById(UUID.fromString(sessionId))) {
            logger.error("Session with id " + sessionId + "not found");
            throw new SessionNotFoundException(sessionId);
        }
    }

}
