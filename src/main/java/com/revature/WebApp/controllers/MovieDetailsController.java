package com.revature.WebApp.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.WebApp.DTO.MovieDetailsDTO;
import com.revature.WebApp.services.MovieDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Movie Details endpoint. Use this endpoint to get detailed information about movies by IMDbID.
 * IMDbID parameter included in URL: .../movieDetails/{imdbId}
 * This will check the remote cache to see if the movie details have already been retrieved. If they exist in our
 * database, they are returned from there, otherwise a call is placed to OMDb, and those details are saved.
 * Checks to make sure there are free calls left to the OMDb API in this time period.
 */
@RestController
public class MovieDetailsController {
    private final ObjectMapper json;
    private final MovieDetailsService movieDetailsService;

    @Autowired
    public MovieDetailsController(MovieDetailsService movieDetailsService) {
        this.movieDetailsService = movieDetailsService;
        json = new ObjectMapper();
    }

    /**
     *
     * @param imdbId - IMDb ID (can be obtained from RapidMDB Search, begins with "tt...".
     * @param response - HTTP response object used to reply to caller
     * @return - MovieDetails JSON - containing returned details about the movie.
     * @throws IOException
     */
    @GetMapping(value="/movieDetails/{imdbId}", produces="application/json")
    public String imdbIdSearch(@PathVariable String imdbId, HttpServletResponse response) throws IOException {
        MovieDetailsDTO detailsObject = movieDetailsService.getMovieDetailsByImdbId(imdbId);

        if(detailsObject == null) {
            response.setStatus(503);
            return "No more calls to OMDb API are allowed in this time period.";
        }
        response.setStatus(200);
        return json.writeValueAsString(detailsObject);
    }
}
