package com.switchfully.codecoach.api.mappers;

import com.switchfully.codecoach.api.dto.CoachFeedbackDTO;
import com.switchfully.codecoach.domain.CoachFeedback;
import org.springframework.stereotype.Component;

@Component
public class CoachFeedbackMapper {

    public CoachFeedback toEntity(CoachFeedbackDTO coachFeedbackDTO) {
        return CoachFeedback.builder()
                .positives(coachFeedbackDTO.getPositives())
                .clarity(coachFeedbackDTO.getClarity())
                .workingPoints(coachFeedbackDTO.getWorkingPoints())
                .usefulness(coachFeedbackDTO.getUsefulness())
                .build();

    }

    public CoachFeedbackDTO toDTO(CoachFeedback coachFeedback) {

        return CoachFeedbackDTO.builder()
                .positives(coachFeedback.getPositives())
                .clarity(coachFeedback.getClarity())
                .workingPoints(coachFeedback.getWorkingPoints())
                .usefulness(coachFeedback.getUsefulness())
                .build();
    }
}
