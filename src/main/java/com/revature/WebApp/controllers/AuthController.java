package com.revature.WebApp.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.WebApp.DTO.LoginDTO;
import com.revature.WebApp.DTO.Principal;
import com.revature.WebApp.security.JwtConfig;
import com.revature.WebApp.security.TokenGenerator;
import com.revature.WebApp.services.MovieUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.revature.WebApp.entities.UserEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


/**
 * This controller is used to handle requests to register and authenticate users. /auth is the registration endpoint,
 * /login is the login endpoint. Login generates a JWT to be stored locally and sent in the header in order to offer
 * stateless multiuser service.
 */
@RestController
@Validated
public class AuthController {
    private MovieUserService movieUserService;
    private TokenGenerator tokenGenerator;
    private JwtConfig jwtConfig;

    @Autowired
    public  AuthController(MovieUserService movieUserService, TokenGenerator tokenGenerator, JwtConfig jwtConfig){
        this.movieUserService = movieUserService;
        this.tokenGenerator = tokenGenerator;
        this.jwtConfig = jwtConfig;

    }

    /**
     *  Registration of a new User into the database
     * @param newUser - The user that you want to register into the database
     * @param response - HTTP response object
     * @return - json formatted string with query results
     * @throws JsonProcessingException
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/auth", consumes = "application/json", produces = "application/json")
    public UserEntity registerUser(@RequestBody @Valid UserEntity newUser, HttpServletResponse response) throws JsonProcessingException {
        UserEntity registeredUser = movieUserService.register(newUser);
        response.setStatus(201);
        response.setHeader("Cache-Control", "no-store");
        return registeredUser;
    }

    /**
     * Uses inputted credentials to look for a specified user
     * @param loginDTO - A DTO representing the username and password that will be used to find a user in the database
     * @param response - HTTP response object
     * @return - json formatted string with updated object (if found)
     */
    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public Principal login(@RequestBody @Valid LoginDTO loginDTO, HttpServletResponse response) {
        Principal foundUser = movieUserService.authenticate(loginDTO.getUsername(), loginDTO.getPassword());
        String jwt = tokenGenerator.createJwt(foundUser);
        response.setStatus(200);
        response.setHeader(jwtConfig.getHeader(), jwt);
        return foundUser;

        //TODO - Invalid?

    }

}
