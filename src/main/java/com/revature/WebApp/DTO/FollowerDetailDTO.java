package com.revature.WebApp.DTO;

import com.revature.WebApp.entities.UserEntity;

public class FollowerDetailDTO {
    private Integer user_id;
    private String username;
    private String userBio;


    public FollowerDetailDTO(){

    }

    public FollowerDetailDTO(UserEntity user){
        this.user_id = user.getUserId();
        this.username = user.getUsername();
        this.userBio = user.getUserBio();
    }
    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserBio() {
        return userBio;
    }

    public void setUserBio(String userBio) {
        this.userBio = userBio;
    }
}

