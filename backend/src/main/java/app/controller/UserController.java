package app.controller;


import app.domain.User;
import app.services.UserService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static app.config.SecurityConstants.HEADER_STRING;
import static app.config.SecurityConstants.TOKEN_PREFIX;


@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * Get a list of all users.
     *
     * @return A list of all users.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    /**
     * Deletes a user by his attributes.
     *
     * @param user The user that should be deleted.
     */
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(User user) {
        this.userService.deleteUser(user);
    }

    /**
     * Return a the username, that matches to the JWT.
     *
     * @param request Request with the JWT:
     * @returns A correct username.
     */
    private String getUsernameByJWT(HttpServletRequest request) {
        String jwt = request.getHeader(HEADER_STRING).substring(TOKEN_PREFIX.length());
        String[] split_string = jwt.split("\\.");
        String base64EncodedBody = split_string[1];

        String payload = new String(new Base64(true).decode(base64EncodedBody));
        JSONObject object = new JSONObject(payload);
        String username = object.getString("sub");
        return username;
    }

    /**
     * Get a user by his JWT.
     *
     * @param request Request with the JWT.
     * @returns The user that matches with the JWT.
     */
    public User getUserByJWT(@Valid HttpServletRequest request) {
        String username = this.getUsernameByJWT(request);
        User matchingUser = userService.findAll().stream().filter(t -> t.getUsername().equals(username)).findFirst().get();
        return matchingUser;
    }

    /**
     * checks if the jwt is valid or not.
     *
     * @param request the request in which the jwt is located.
     * @returns the boolean value if the jwt is valid.
     */
    @RequestMapping("/valid")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public boolean getJWTValid(@Valid HttpServletRequest request) {
        String username = getUsernameByJWT(request);
        boolean userExists = userService.findAll().stream().anyMatch(t -> t.getUsername().equals(username));
        return userExists;
    }

    /**
     * searches for the current user by the jwt.
     *
     * @param request the request in which the jwt is located.
     * @returns the found user.
     */
    @RequestMapping("/current")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public User getCurrentUser(@Valid HttpServletRequest request) {
        User matchingUser = getUserByJWT(request);
        return matchingUser;
    }

    /**
     * the function to allow the creation of a user without needing a jwt to do this process.
     *
     * @param user the user to be created.
     * @returns the created user.
     */
    @RequestMapping("/sign-up")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userService.createUser(user);
    }

    /**
     * updates a specific user.
     *
     * @param user    the user to be updated.
     * @param request the request in which the jwt is located.
     * @returns the updated user.
     */
    @RequestMapping("update")
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@Valid @RequestBody User user, @Valid HttpServletRequest request) {
        user.setId(getUserByJWT(request).getId());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userService.updateUser(user);
    }

    /**
     * deletes a specific user found inside the jwt.
     *
     * @param request the request in which the jwt is located
     */
    @RequestMapping("/delete")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@Valid HttpServletRequest request) {
        User matchingUser = getUserByJWT(request);
        this.userService.deleteUser(matchingUser);
    }
}