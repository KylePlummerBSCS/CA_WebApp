package com.revature.WebApp.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class FollowsDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id")
    private Integer friendsListId;

    @NotNull
    @ManyToOne(targetEntity = UserEntity.class, optional = false)
    @JoinColumn(name = "fk_follower_user_id", referencedColumnName = "user_id", nullable = false)
    private UserEntity followerUserId;

    @NotNull
    @ManyToOne(targetEntity = UserEntity.class, optional = false)
    @JoinColumn(name = "fk_following_user_id", referencedColumnName = "user_id", nullable = false)
    private UserEntity followingUserId;

    @NotNull
    @Column(name = "username")
    private String username;
}
