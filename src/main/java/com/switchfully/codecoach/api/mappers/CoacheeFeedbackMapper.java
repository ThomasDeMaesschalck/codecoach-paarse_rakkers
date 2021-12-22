package com.switchfully.codecoach.api.mappers;

import com.switchfully.codecoach.api.dto.CoacheeFeedbackDTO;
import com.switchfully.codecoach.domain.CoacheeFeedback;
import org.springframework.stereotype.Component;

@Component
public class CoacheeFeedbackMapper {

    public CoacheeFeedback toEntity(CoacheeFeedbackDTO coacheeFeedbackDTO) {
        return CoacheeFeedback.builder()
                .positives(coacheeFeedbackDTO.getPositives())
                .preparedness(coacheeFeedbackDTO.getPreparedness())
                .workingPoints(coacheeFeedbackDTO.getWorkingPoints())
                .willingness(coacheeFeedbackDTO.getWillingness())
                .build();

    }

    public CoacheeFeedbackDTO toDTO(CoacheeFeedback coacheeFeedback) {

        return CoacheeFeedbackDTO.builder()
                .positives(coacheeFeedback.getPositives())
                .preparedness(coacheeFeedback.getPreparedness())
                .workingPoints(coacheeFeedback.getWorkingPoints())
                .willingness(coacheeFeedback.getWillingness())
                .build();
    }
}
