package com.revature.WebApp.services;

import com.revature.WebApp.DTO.Principal;
import com.revature.WebApp.entities.UserEntity;
import com.revature.WebApp.exceptions.*;
import com.revature.WebApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MovieUserService {

    private UserRepository movieUserRepo;

    @Autowired
    public MovieUserService(UserRepository movieUserDao) {
        this.movieUserRepo = movieUserDao;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UserEntity register(UserEntity newMovieUser) throws InvalidRequestException, ResourcePersistenceException {

        isUserValid(newMovieUser);

        if (isUsernameAvailable(newMovieUser.getUsername())) {
            throw new ResourcePersistenceException("Provided username is already taken!");
        }

        if (isEmailAvailable(newMovieUser.getEmail())) {
            throw new ResourcePersistenceException("Provided email is already taken!");
        }

//        newMovieUser.setRole(UserEntity.Role.BASIC_USER);
        return movieUserRepo.save(newMovieUser);
    }

    public boolean isUserValid(UserEntity u) throws InvalidRequestException {

        if (u == null)
            throw new InvalidRequestException("A null user was provided.");

        if (!isValid(u.getUsername(), "username"))
            throw new InvalidRequestException("An invalid username was provided.");

        if (!isValid(u.getPassword(), "password"))
            throw new InvalidRequestException("An invalid password was provided.");

        if (!isValid(u.getEmail(), "email"))
            throw new InvalidRequestException("An invalid email was provided.");

        if (!isValid(u.getFirstName(), "firstName"))
            throw new InvalidRequestException("An invalid first name was provided.");

        if (!isValid(u.getLastName(), "lastName"))
            throw new InvalidRequestException("An invalid last name was provided.");

        return true;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public boolean isEmailAvailable(String email) {

        if (!isValid(email, "email"))
            throw new InvalidRequestException("Invalid email value provided!");

        try {
            return movieUserRepo.isEmailAvailable(email);
        } catch (Exception e) {
            if (e instanceof ResourceNotFoundException) throw e;
            throw new DataSourceException(e);
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public boolean isUsernameAvailable(String username) {

        if (!isValid(username, "username"))
            throw new InvalidRequestException("Invalid username value provided!");

        try {
            return movieUserRepo.isUsernameAvailable(username);
        } catch (Exception e) {
            throw new DataSourceException(e);
        }

    }

    @Transactional(readOnly = true)
    public Principal authenticate(String username, String password) throws AuthenticationException {

        if (!isValid(username, "username") || !isValid(password, "password"))
            throw new InvalidRequestException("Invalid username and/or password provided!");

        try {

            UserEntity authUser = movieUserRepo.findByUsernameAndPassword(username, password)
                    .orElseThrow(AuthenticationException::new);

            return new Principal(authUser);

        } catch (Exception e) {
            if (e instanceof ResourceNotFoundException) throw e;
            throw new DataSourceException(e);
        }

    }

    public boolean isValid(String str, String fieldName) {

        if (str == null || str.trim().isEmpty()) return false;
        if (fieldName == null || fieldName.trim().isEmpty()) return false;

        switch (fieldName) {
            case "username":
                return str.length() <= 20;
            case "firstName":
            case "lastName":
                return str.length() <= 25;
            case "password":
            case "email":
                return str.length() <= 255;
//            case "role":
//                try {
//                    UserEntity.Role.valueOf(str);
//                    return true;
//                } catch (IllegalArgumentException e) {
//                    return false;
//                }
            default:
                return false;
        }


    }


}
