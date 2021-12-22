package com.switchfully.codecoach.repository;

import com.switchfully.codecoach.domain.Session;
import com.switchfully.codecoach.domain.SessionStatus;
import com.switchfully.codecoach.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, UUID> {

    List<Session> findAllByCoachee(User coachee);

    List<Session> findAllByCoachOrCoachee(User user, User user2);

    int countAllByCoachAndCoachFeedbackNotNullAndCoacheeFeedbackNotNull(User coach);

    List<Session> findAllByStatusNotAndMomentIsBefore(SessionStatus status, LocalDateTime moment);

}
