package com.revature.WebApp.repositories;

import com.revature.WebApp.entities.MovieDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for CRUD access to the movie_cache table
 */
@Repository
public interface MovieDetailsRepository extends JpaRepository<MovieDetailsEntity, String> {
    MovieDetailsEntity findByImdbId(String imdbId);

    @Query(nativeQuery = true, value = "SELECT * FROM movie_cache mc INNER JOIN watch_history wh on wh.fk_movie_id = mc.imdb_id WHERE wh.fk_user_id = ?1")
    List<MovieDetailsEntity> findWatchHistoryDetails(Integer userId);

    @Query(nativeQuery = true, value = "SELECT * FROM movie_cache mc INNER JOIN watchlist wl on wl.fk_movie_id = mc.imdb_id WHERE wl.fk_user_id = ?1")
    List<MovieDetailsEntity> findWatchListDetails(Integer userId);
}
