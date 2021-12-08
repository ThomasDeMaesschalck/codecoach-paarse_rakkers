package com.switchfully.codecoach.repository;

import com.switchfully.codecoach.domain.Session;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, UUID> {
}
