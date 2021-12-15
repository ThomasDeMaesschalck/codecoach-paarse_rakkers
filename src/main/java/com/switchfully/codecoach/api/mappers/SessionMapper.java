package com.switchfully.codecoach.api.mappers;

import com.switchfully.codecoach.api.dto.SessionDTO;
import com.switchfully.codecoach.domain.Session;
import com.switchfully.codecoach.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionMapper {

    private final CoacheeFeedbackMapper coacheeFeedbackMapper;
    private final CoachFeedbackMapper coachFeedbackMapper;

    @Autowired
    public SessionMapper(CoacheeFeedbackMapper coacheeFeedbackMapper, CoachFeedbackMapper coachFeedbackMapper) {
        this.coacheeFeedbackMapper = coacheeFeedbackMapper;
        this.coachFeedbackMapper = coachFeedbackMapper;
    }

    public Session toEntity(SessionDTO sessionDTO, User coach, User coachee){
        return Session.builder()
                .coach(coach)
                .subject(sessionDTO.getSubject())
                .coachee(coachee)
                .moment(sessionDTO.getMoment())
                .faceToFace(sessionDTO.isFaceToFace())
                .remarks(sessionDTO.getRemarks())
                .status(sessionDTO.getStatus())
                .coachFeedback(sessionDTO.getCoachFeedback() == null ? null : coachFeedbackMapper.toEntity(sessionDTO.getCoachFeedback()))
                .coacheeFeedback(sessionDTO.getCoacheeFeedback() == null ? null : coacheeFeedbackMapper.toEntity(sessionDTO.getCoacheeFeedback()))
            .build();
    }

    public SessionDTO toDTO(Session session){
        return SessionDTO.builder()
                .id(session.getId())
                .subject(session.getSubject())
                .coachId(session.getCoach().getId().toString())
                .coacheeId(session.getCoachee().getId().toString())
                .faceToFace(session.isFaceToFace())
                .moment(session.getMoment())
                .remarks(session.getRemarks())
                .coachFeedback(session.getCoachFeedback() == null ? null : coachFeedbackMapper.toDTO(session.getCoachFeedback()))
                .coacheeFeedback(session.getCoacheeFeedback() == null ? null : coacheeFeedbackMapper.toDTO(session.getCoacheeFeedback()))
                .status(session.getStatus())
                .build();

    }


}
