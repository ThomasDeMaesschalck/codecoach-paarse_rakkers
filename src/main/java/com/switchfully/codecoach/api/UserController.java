package com.switchfully.codecoach.api;

import com.switchfully.codecoach.api.dto.UpdateUserDTO;
import com.switchfully.codecoach.api.dto.UserDTO;
import com.switchfully.codecoach.domain.Topic;
import com.switchfully.codecoach.domain.UserRole;
import com.switchfully.codecoach.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @SecurityRequirement(name = "javainuseapi")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllUsers(@RequestParam(required = false) Topic.TopicName topic,
                                     @RequestParam(required = false) UserRole role,
                                     @RequestParam(required = false) String partialSearch
    ) {
        logger.info("Getting all users");
        return userService.getAllUsers(topic, role, partialSearch);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public UserDTO registerUser(@Valid @RequestBody UserDTO userDTO) {
        logger.info("Registering new user");
        return userService.registerUser(userDTO);
    }

    @SecurityRequirement(name = "javainuseapi")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserDTO updateUser(@Valid @RequestBody UpdateUserDTO updateUserDTO, @PathVariable String id,
                              @RequestHeader(name = "Authorization") String token) {
        logger.info("Updating user with id " + id);
        return userService.updateUser(UUID.fromString(id), updateUserDTO, token);
    }

    @SecurityRequirement(name = "javainuseapi")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getSpecificUser(@PathVariable String id, @RequestHeader(name = "Authorization") String token) {

        logger.info("Getting user with id " + id);
        return userService.getUserDTO(id, token);
    }


}
