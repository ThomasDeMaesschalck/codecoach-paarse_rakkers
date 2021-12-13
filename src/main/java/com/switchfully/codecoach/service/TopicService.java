package com.switchfully.codecoach.service;

import com.switchfully.codecoach.domain.Topic;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicService {

    public List<String> getTopicNames() {
        return Arrays.stream(Topic.TopicName.values())
                .map(Topic.TopicName::toString)
                .collect(Collectors.toList());
    }
}
