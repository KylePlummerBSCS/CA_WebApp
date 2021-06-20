package com.revature.WebApp.DTO;



public class friendTest {
    private String username;
    private String fk_follower_user_id;

    public friendTest(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFk_follower_user_id() {
        return fk_follower_user_id;
    }

    public void setFk_follower_user_id(String fk_follower_user_id) {
        this.fk_follower_user_id = fk_follower_user_id;
    }
}
