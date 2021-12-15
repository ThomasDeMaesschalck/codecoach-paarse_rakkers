package com.switchfully.codecoach.service;

import com.switchfully.codecoach.api.dto.SessionDTO;
import com.switchfully.codecoach.api.mappers.CoachFeedbackMapper;
import com.switchfully.codecoach.api.mappers.CoacheeFeedbackMapper;
import com.switchfully.codecoach.api.mappers.SessionMapper;
import com.switchfully.codecoach.domain.Session;
import com.switchfully.codecoach.domain.SessionStatus;
import com.switchfully.codecoach.domain.User;
import com.switchfully.codecoach.domain.UserRole;
import com.switchfully.codecoach.exception.SessionNotFoundException;
import com.switchfully.codecoach.repository.SessionRepository;
import com.switchfully.codecoach.repository.UserRepository;
import com.switchfully.codecoach.security.authentication.jwt.JwtGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class SessionService {

    private final SessionRepository sessionRepository;
    private final SessionMapper sessionMapper;
    private final UserService userService;
    private final JwtGenerator jwtGenerator;
    private final UserRepository userRepository;
    private final CoachFeedbackMapper coachFeedbackMapper;
    private final CoacheeFeedbackMapper coacheeFeedbackMapper;

    private final Logger logger = LoggerFactory.getLogger(SessionService.class);


    @Autowired
    public SessionService(SessionRepository sessionRepository,
                          SessionMapper sessionMapper,
                          UserService userService,
                          JwtGenerator jwtGenerator,
                          UserRepository userRepository, CoachFeedbackMapper coachFeedbackMapper, CoacheeFeedbackMapper coacheeFeedbackMapper) {
        this.sessionRepository = sessionRepository;
        this.sessionMapper = sessionMapper;
        this.userService = userService;
        this.jwtGenerator = jwtGenerator;
        this.userRepository = userRepository;
        this.coachFeedbackMapper = coachFeedbackMapper;
        this.coacheeFeedbackMapper = coacheeFeedbackMapper;
    }


    public void setSessionsStatusToAwaitingFeedbackOrDoneIfLocalDateTimeIsInPast() {
        List<Session> expiredSessions = sessionRepository.findAllByStatusNotAndMomentIsBefore(SessionStatus.DONE, LocalDateTime.now());

        expiredSessions.forEach(session -> {
            if (session.getStatus().equals(SessionStatus.ACCEPTED)) {
                session.setStatus(SessionStatus.DONE_WAITING_FEEDBACK);
            } else if (session.getStatus().equals(SessionStatus.REQUESTED)) {
                session.setStatus(SessionStatus.DONE);
            }
        });

        sessionRepository.saveAll(expiredSessions);
    }


    public SessionDTO saveSession(SessionDTO dto) {
        User coach = userService.getSpecificUserById(dto.getCoachId());
        User coachee = userService.getSpecificUserById(dto.getCoacheeId());
        return sessionMapper.toDTO(sessionRepository.save(sessionMapper.toEntity(dto, coach, coachee)));
    }

    public SessionDTO updateSession(String sessionId, SessionDTO dto, String authToken) {
        Session sessionToUpdate = getSession(sessionId);

        var authorization = jwtGenerator.convertToken(authToken);
        User caller = userRepository.findByEmail(authorization.getName()).get();


        switch (sessionToUpdate.getStatus()) {

            case REQUESTED:

                if (dto.getStatus() == SessionStatus.ACCEPTED && sessionToUpdate.getCoach().equals(caller)) {
                    sessionToUpdate.setStatus(SessionStatus.ACCEPTED);
                }
                if (dto.getStatus() == SessionStatus.DECLINED && sessionToUpdate.getCoach().equals(caller)) {
                    sessionToUpdate.setStatus(SessionStatus.DECLINED);
                }
                if (dto.getStatus() == SessionStatus.CANCELED_BY_COACHEE && sessionToUpdate.getCoachee().equals(caller)) {
                    sessionToUpdate.setStatus(SessionStatus.CANCELED_BY_COACHEE);
                }
                break;

            case ACCEPTED:

                if (dto.getStatus() == SessionStatus.CANCELED_BY_COACH && sessionToUpdate.getCoach().equals(caller)) {
                    sessionToUpdate.setStatus(SessionStatus.CANCELED_BY_COACH);
                }
                if (dto.getStatus() == SessionStatus.CANCELED_BY_COACHEE && sessionToUpdate.getCoachee().equals(caller)) {
                    sessionToUpdate.setStatus(SessionStatus.CANCELED_BY_COACHEE);
                }
                break;

            case DONE_WAITING_FEEDBACK:
                if(dto.getCoachFeedback() != null && sessionToUpdate.getCoach().equals(caller)) {
                    sessionToUpdate.setCoachFeedback(coachFeedbackMapper.toEntity(dto.getCoachFeedback()));
                    closeSessionIfFeedbackIsGivenByBothParties(sessionToUpdate);
                }

                if(dto.getCoacheeFeedback() != null && sessionToUpdate.getCoachee().equals(caller)) {
                    sessionToUpdate.setCoacheeFeedback(coacheeFeedbackMapper.toEntity(dto.getCoacheeFeedback()));
                    closeSessionIfFeedbackIsGivenByBothParties(sessionToUpdate);
                }
        }

        return sessionMapper.toDTO(sessionToUpdate);
    }

    public List<SessionDTO> getAllSessions(String coachId) {
        setSessionsStatusToAwaitingFeedbackOrDoneIfLocalDateTimeIsInPast();

        if (coachId != null) {
            User coach = userService.getSpecificUserById(coachId);
            userService.assertUserHasRoles(coach, UserRole.COACH, UserRole.ADMIN);
            return sessionRepository.findAllByCoach(coach).stream()
                    .map(sessionMapper::toDTO)
                    .collect(Collectors.toList());
        } else
            return sessionRepository.findAll().stream()
                    .map(sessionMapper::toDTO)
                    .collect(Collectors.toList());

    }

    public SessionDTO getSessionDTO(String sessionId) {
        return sessionMapper.toDTO(getSession(sessionId));
    }

    public Session getSession(String sessionId) {
        assertSessionExists(sessionId);
        return sessionRepository.findById(UUID.fromString(sessionId)).get();
    }

    public void assertSessionExists(String sessionId) {
        if (!sessionRepository.existsById(UUID.fromString(sessionId))) {
            logger.error("Session with id " + sessionId + "not found");
            throw new SessionNotFoundException(sessionId);
        }
    }

    public void closeSessionIfFeedbackIsGivenByBothParties(Session session) {
        if (session.getCoachFeedback() != null && session.getCoacheeFeedback() != null )
        {
            session.setStatus(SessionStatus.DONE);
        }
    }

}
