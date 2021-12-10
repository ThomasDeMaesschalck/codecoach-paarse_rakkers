package com.switchfully.codecoach.repository;

import com.switchfully.codecoach.domain.Session;

import com.switchfully.codecoach.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, UUID> {

    List<Session> findAllByCoach(User coach);
}
