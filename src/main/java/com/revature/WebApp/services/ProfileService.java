package com.revature.WebApp.services;

import com.revature.WebApp.DTO.MovieReviewDTO;
import com.revature.WebApp.entities.MovieDetailsEntity;
import com.revature.WebApp.entities.UserEntity;
import com.revature.WebApp.entities.WatchHistoryEntity;
import com.revature.WebApp.entities.WatchlistEntity;
import com.revature.WebApp.exceptions.ResourceNotFoundException;
import com.revature.WebApp.repositories.MovieDetailsRepository;
import com.revature.WebApp.repositories.UserRepository;
import com.revature.WebApp.repositories.WatchHistoryRepository;
import com.revature.WebApp.repositories.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfileService {
    private final WatchHistoryRepository historyRepo;
    private final WatchlistRepository watchListRepo;
    private final MovieDetailsRepository movieRepo;
    private final UserRepository userRepo;

    @Autowired
    public ProfileService(WatchHistoryRepository historyRepo, WatchlistRepository watchListRepo, MovieDetailsRepository movieRepo, UserRepository userRepo){
        this.historyRepo = historyRepo;
        this.watchListRepo = watchListRepo;
        this.movieRepo = movieRepo;
        this.userRepo = userRepo;

    }


    public WatchHistoryEntity getHistoryEntity(Integer userId, MovieReviewDTO reviewDTO) throws ResourceNotFoundException {
        WatchHistoryEntity historyEntity = historyRepo.findByUserIdAndMovieId(userId, reviewDTO.getImdbId())
                .orElseThrow(ResourceNotFoundException::new);
        historyEntity.setScore(reviewDTO.getScore());
        historyEntity.setReview(reviewDTO.getReview());
        historyRepo.save(historyEntity);
        return historyEntity;
    }


    public boolean addToHistory(Integer userId, String imdbId) throws ResourceNotFoundException {
        if (watchListRepo.checkIfMovieAlreadyExists(imdbId, userId) > 0) {
            System.out.println("Movie does already exist? " + imdbId + ", " + watchListRepo.checkIfMovieAlreadyExists(imdbId, userId));
            return true;
        }
        System.out.println("userId: " + userId + ", imdbId: " + imdbId);

        UserEntity user = userRepo.findByUserId(userId);
        MovieDetailsEntity movie = movieRepo.findByImdbId(imdbId);

        System.out.println("\n\n\n USER TOSTRING: " + user.toString());
        System.out.println("MOVIE TOSTRING: " + movie.toString());

        WatchHistoryEntity entity = new WatchHistoryEntity(user, movie);

        historyRepo.save(entity);
        return true;
        //TODO: Add checking logic here, not always returning true...
    }



    public boolean addToWatchList(Integer userId, String imdbId) throws ResourceNotFoundException {
        System.out.println("userId: " + userId + ", imdbId: " + imdbId);

        UserEntity user = userRepo.findByUserId(userId);
        MovieDetailsEntity movie = movieRepo.findByImdbId(imdbId);

        System.out.println(user.toString());
        System.out.println(movie.toString());

        WatchlistEntity entity = new WatchlistEntity(user, movie);

        watchListRepo.save(entity);
        return true;
        //TODO: Add checking logic here, not always returning true...
    }

}
