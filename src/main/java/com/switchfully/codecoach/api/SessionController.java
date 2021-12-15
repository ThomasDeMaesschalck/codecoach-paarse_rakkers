package com.switchfully.codecoach.api;

import com.switchfully.codecoach.api.dto.SessionDTO;
import com.switchfully.codecoach.api.dto.UpdateUserDTO;
import com.switchfully.codecoach.api.dto.UserDTO;
import com.switchfully.codecoach.domain.SessionStatus;
import com.switchfully.codecoach.service.SessionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.UUID;

@RestController
//@CrossOrigin
@RequestMapping("/sessions")
@SecurityRequirement(name = "javainuseapi")
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
    //@PreAuthorize("hasAnyRole()")
    public SessionDTO saveSession(@Valid @RequestBody SessionDTO sessionDTO) {
        logger.info("Saving a new session");
        return sessionService.saveSession(sessionDTO);
    }


    @GetMapping (produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    //@PreAuthorize("hasAnyRole()")
    public List<SessionDTO> getAllSessions(@RequestParam(required = false) String coachId) {
        logger.info("Getting all sessions");
        return sessionService.getAllSessions(coachId);
    }

    @GetMapping (produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    //@PreAuthorize("hasAnyRole()")
    public SessionDTO getSpecificSession(@PathVariable String id) {
        logger.info("Getting session with id " + id);
        return sessionService.getSessionDTO(id);
    }
}
