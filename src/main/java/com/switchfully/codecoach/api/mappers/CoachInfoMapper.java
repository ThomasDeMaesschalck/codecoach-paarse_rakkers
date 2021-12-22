package com.switchfully.codecoach.api.mappers;

import com.switchfully.codecoach.api.dto.CoachInfoDTO;
import com.switchfully.codecoach.domain.CoachInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CoachInfoMapper {

    private final TopicMapper topicMapper;

    @Autowired
    public CoachInfoMapper(TopicMapper topicMapper) {
        this.topicMapper = topicMapper;
    }

    public CoachInfo toEntity(CoachInfoDTO coachInfoDTO) {
        return CoachInfo.builder()
                .availability(coachInfoDTO.getAvailability())
                .introduction(coachInfoDTO.getIntroduction())
                .topics(coachInfoDTO.getTopics().stream().map(topicMapper::toEntity).collect(Collectors.toList()))
                .build();

    }

    public CoachInfoDTO toDTO(CoachInfo coachInfo) {
        return CoachInfoDTO.builder()
                .introduction(coachInfo.getIntroduction())
                .availability(coachInfo.getAvailability())
                .topics((coachInfo.getTopics().stream().map(topicMapper::toDTO).collect(Collectors.toList())))
                .build();
    }
}
