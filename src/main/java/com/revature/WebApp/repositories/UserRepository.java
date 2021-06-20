package com.revature.WebApp.repositories;

import com.revature.WebApp.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for CRUD access to the users table
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByUserId(Integer userId);

    Optional<UserEntity> findByUsernameAndPassword(String username, String password);

    @Query("select case when count(u) > 0 then true else false end from UserEntity u where u.email = :email")
    boolean isEmailAvailable(String email);

    @Query("select case when count(u) > 0 then true else false end from UserEntity u where u.username = :username")
    boolean isUsernameAvailable(String username);

    @Query(nativeQuery = true, value = "SELECT u.* FROM follows_list fl INNER JOIN users u ON (fl.fk_following_user_id = u.user_id) WHERE fl.fk_follower_user_id = ?1")
    public List<UserEntity> getFollowerDetail(Integer userId);



}
