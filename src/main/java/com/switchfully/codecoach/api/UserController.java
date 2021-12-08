package com.switchfully.codecoach.api;

import com.switchfully.codecoach.api.dto.UserDTO;
import com.switchfully.codecoach.service.UserService;
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
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping (produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    //@PreAuthorize("hasAnyRole()")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    //@PreAuthorize("hasAnyRole()")
    public UserDTO registerUser(@Valid @RequestBody UserDTO userDTO) {
        logger.info("Registering new user");
        return userService.registerUser(userDTO);
    }

    @GetMapping (produces = MediaType.APPLICATION_JSON_VALUE, path = "/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    //@PreAuthorize("hasAnyRole()")
    public UserDTO getSpecificUser(@PathVariable String id) {
        logger.info("Getting user with id " + id);
        return userService.getUserDTO(id);
    }


}
