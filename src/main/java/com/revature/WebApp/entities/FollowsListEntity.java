package com.revature.WebApp.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Entity representing a row in the database follows_list table.
 * Contains info linking current user to the user's they follow,
 * allowing us to produce a list of profiles to view.
 */
@Entity
@Table(name = "follows_list")
public class FollowsListEntity {
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
}
