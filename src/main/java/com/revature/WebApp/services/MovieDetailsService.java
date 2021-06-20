package com.revature.WebApp.services;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.WebApp.APIAccess.OMDbAPI;
import com.revature.WebApp.DTO.MovieDetailsDTO;
import com.revature.WebApp.DTO.OMDbSearchResultsDTO;
import com.revature.WebApp.entities.MovieDetailsEntity;
import com.revature.WebApp.entities.UserEntity;
import com.revature.WebApp.repositories.MovieDetailsRepository;
import com.revature.WebApp.repositories.WatchHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MovieDetailsService {
    private MovieDetailsRepository movieRepo;

    private ObjectMapper json;
    private OMDbAPI omdbApi;

    @Autowired
    public MovieDetailsService(MovieDetailsRepository movieRepo, OMDbAPI omdbApi) {
        this.movieRepo = movieRepo;
        this.json = new ObjectMapper();
        this.omdbApi = omdbApi;
    }

    public void saveMovieDetailsCache() {

    }

    public MovieDetailsDTO getMovieDetailsByImdbId(String imdbId) throws IOException {
        MovieDetailsEntity foundMovie = movieRepo.findByImdbId(imdbId);
        if(foundMovie == null) {
            //not found, get from API
            OMDbSearchResultsDTO movieDetails = omdbApi.searchByImdbId(imdbId);
            MovieDetailsEntity entity = new MovieDetailsEntity(movieDetails);
            movieRepo.save(entity);
            return new MovieDetailsDTO(movieDetails);
        }
        return new MovieDetailsDTO(foundMovie);

    }

    public List<MovieDetailsDTO> getWatchHistoryDetails(Integer userId) {

        List<MovieDetailsEntity> searchResults = movieRepo.findWatchHistoryDetails(userId);
        List<MovieDetailsDTO> list = new ArrayList<>();
        for (MovieDetailsEntity searchResult : searchResults) {
            list.add(new MovieDetailsDTO(searchResult));
        }
        return list;
    }

    public List<MovieDetailsDTO> getWishListDetails(Integer userId) {

        List<MovieDetailsEntity> searchResults = movieRepo.findWatchListDetails(userId);
        List<MovieDetailsDTO> list = new ArrayList<>();
        for (MovieDetailsEntity searchResult : searchResults) {
            list.add(new MovieDetailsDTO(searchResult));
        }
        return list;
    }

}
