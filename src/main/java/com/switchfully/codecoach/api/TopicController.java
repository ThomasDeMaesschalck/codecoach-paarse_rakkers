package com.switchfully.codecoach.api;

import com.switchfully.codecoach.service.TopicService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topics")
@SecurityRequirement(name = "javainuseapi")
public class TopicController {

    private final Logger logger = LoggerFactory.getLogger(TopicController.class);
    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<String> getAllTopics() {
        logger.info("Getting all topics");
        return topicService.getTopicNames();
    }
}
