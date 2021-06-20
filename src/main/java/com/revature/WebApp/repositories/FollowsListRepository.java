package com.revature.WebApp.repositories;

import com.revature.WebApp.entities.FollowsDetailEntity;
import com.revature.WebApp.entities.FollowsListEntity;
import com.revature.WebApp.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;


/**
 * Repository interface for CRUD access to the follows_list table
 */
@Repository
public interface FollowsListRepository extends JpaRepository<FollowsListEntity, Integer> {

}
