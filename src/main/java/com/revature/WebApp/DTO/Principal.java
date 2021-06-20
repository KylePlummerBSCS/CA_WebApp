package com.revature.WebApp.DTO;

import com.revature.WebApp.entities.UserEntity;
import io.jsonwebtoken.Claims;

/**
 * DTO for JWT auth data
 */
public class Principal {

    private int id;
    private String username;
    //private UserEntity.Role role;

    public Principal() {
        super();
    }

    public Principal(Claims jwtClaims) {
        this.id = Integer.parseInt(jwtClaims.getId());
        this.username = jwtClaims.getSubject();
        //this.role = UserEntity.Role.valueOf(jwtClaims.get("role", String.class).toUpperCase());
    }

    public Principal(UserEntity user) {
        this.id = user.getUserId();
        this.username = user.getUsername();
        //this.role = user.getRole();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}