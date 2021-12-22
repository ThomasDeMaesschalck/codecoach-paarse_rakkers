package com.switchfully.codecoach.api;

import com.switchfully.codecoach.api.dto.SessionDTO;
import com.switchfully.codecoach.service.SessionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sessions")
public class SessionController {


    private final SessionService sessionService;
    private final Logger logger = LoggerFactory.getLogger(SessionController.class);

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public SessionDTO saveSession(@Valid @RequestBody SessionDTO sessionDTO) {
        logger.info("Saving a new session");
        return sessionService.saveSession(sessionDTO);
    }

    @SecurityRequirement(name = "javainuseapi")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<SessionDTO> getAllSessions(@RequestParam(required = false) String userId) {
        logger.info("Getting all sessions");
        return sessionService.getAllSessions(userId);
    }

    @SecurityRequirement(name = "javainuseapi")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SessionDTO getSpecificSession(@PathVariable String id) {
        logger.info("Getting session with id " + id);
        return sessionService.getSessionDTO(id);
    }

    @SecurityRequirement(name = "javainuseapi")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public SessionDTO updateSession(@Valid @RequestBody SessionDTO updateSessionDTO, @PathVariable String id,
                                    @RequestHeader(name = "Authorization") String token) {
        logger.info("Updating user with id " + id);
        return sessionService.updateSession(id, updateSessionDTO, token);
    }
}
