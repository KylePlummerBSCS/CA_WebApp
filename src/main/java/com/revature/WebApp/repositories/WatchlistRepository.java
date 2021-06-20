package com.revature.WebApp.repositories;

import com.revature.WebApp.entities.WatchlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for CRUD access to the watchlist table
 */
@Repository
public interface WatchlistRepository  extends JpaRepository<WatchlistEntity, Integer> {
    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM watch_history WHERE fk_movie_id = ?1 AND fk_user_id = ?2")
    Integer checkIfMovieAlreadyExists(String imdbId, Integer userId);

}
