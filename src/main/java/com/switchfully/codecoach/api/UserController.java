package com.switchfully.codecoach.api;

public class UserController {

    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping (produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
  //  @PreAuthorize("hasAnyRole()")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    //@PreAuthorize("hasAnyRole()")
    public UserDTO registerUser(@RequestBody UserDTO userDTO) {
        logger.info("Registering new user");
        return userService.registerUser(userDTO);
    }

}
