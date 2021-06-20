package com.revature.WebApp.repositories;

import com.revature.WebApp.entities.FollowsListEntity;
import com.revature.WebApp.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowsDetailRepository extends JpaRepository<FollowsListEntity, Integer> {

}
