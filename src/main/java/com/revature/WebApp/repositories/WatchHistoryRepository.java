package com.revature.WebApp.repositories;

import com.revature.WebApp.entities.UserEntity;
import com.revature.WebApp.entities.WatchHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for CRUD access to the watch_history table
 */
@Repository
public interface WatchHistoryRepository  extends JpaRepository<WatchHistoryEntity, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM watch_history WHERE fk_user_id = ?1 AND fk_movie_id = ?2")
    Optional<WatchHistoryEntity> findByUserIdAndMovieId(Integer userId, String movieId);


}
