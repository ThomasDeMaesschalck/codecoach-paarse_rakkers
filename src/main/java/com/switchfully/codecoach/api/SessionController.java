package com.switchfully.codecoach.api;

import com.switchfully.codecoach.api.dto.SessionDTO;
import com.switchfully.codecoach.api.dto.UserDTO;
import com.switchfully.codecoach.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//@CrossOrigin
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
    //@PreAuthorize("hasAnyRole()")
    public SessionDTO saveSession(@Valid @RequestBody SessionDTO sessionDTO) {
        logger.info("Saving a new session");
        return sessionService.saveSession(sessionDTO);
    }


    @GetMapping (produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    //@PreAuthorize("hasAnyRole()")
    public List<SessionDTO> getAllSessions() {
        logger.info("Getting all sessions");
        return sessionService.getAllSessions();
    }
}
