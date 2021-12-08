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
                .coachee(coachee)
                .moment(sessionDTO.getMoment())
                .faceToFace(sessionDTO.isFaceToFace())
                .remarks(sessionDTO.getRemarks())
                .status(sessionDTO.getStatus())
                .coachFeedback(coachFeedbackMapper.toEntity(sessionDTO.getCoachFeedback()))
                .coacheeFeedback(coacheeFeedbackMapper.toEntity(sessionDTO.getCoacheeFeedback()))
            .build();
    }

    public SessionDTO toDTO(Session session){
        return SessionDTO.builder()
                .coachId(session.getCoach().getId().toString())
                .coacheeId(session.getCoachee().getId().toString())
                .faceToFace(session.isFaceToFace())
                .moment(session.getMoment())
                .remarks(session.getRemarks())
                .coacheeFeedback(coacheeFeedbackMapper.toDTO(session.getCoacheeFeedback()))
                .coachFeedback(coachFeedbackMapper.toDTO(session.getCoachFeedback()))
                .status(session.getStatus())
                .build();

    }


}
