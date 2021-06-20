package com.revature.WebApp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.WebApp.DTO.MovieDetailsDTO;
import com.revature.WebApp.DTO.Principal;
import com.revature.WebApp.security.TokenParser;
import com.revature.WebApp.services.MovieDetailsService;
import com.revature.WebApp.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class LandingPageController {
    private final ObjectMapper json;
    private final MovieDetailsService movieService;
    private final ProfileService profileService;
    private final TokenParser tokenParser;

    @Autowired
    public LandingPageController(TokenParser tokenParser, MovieDetailsService movieService, ProfileService profileService) {
        //this.watchHistoryRepo = watchHistoryRepo;
        this.tokenParser = tokenParser;
        this.movieService = movieService;
        this.json = new ObjectMapper();
        this.profileService = profileService;
    }

    @PutMapping(value="/addToHistory/{imdbId}")
    public void addToHistory(@PathVariable String imdbId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        tokenParser.checkToken(request);
        if(request.getAttribute("principal") == null) {
            response.setStatus(401);
            return;
        }

        Integer userId = ((Principal)request.getAttribute("principal")).getId();
        profileService.addToHistory(userId, imdbId);

        response.setStatus(200);
    }


    @GetMapping(value="/history", produces = "application/json")
    public String watchHistorySearch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        tokenParser.checkToken(request);
        if(request.getAttribute("principal") == null) {
            response.setStatus(401);
            return "Not Authorized.";
        }

        Integer userId = ((Principal)request.getAttribute("principal")).getId();
        List<MovieDetailsDTO> list =  movieService.getWatchHistoryDetails(userId);

        response.setStatus(200);
        return json.writeValueAsString(list);
    }

    @GetMapping(value="/wishlist", produces = "application/json")
    public String wishListSearch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        tokenParser.checkToken(request);
        if(request.getAttribute("principal") == null) {
            response.setStatus(401);
            return "Not Authorized.";
        }

        Integer userId = ((Principal)request.getAttribute("principal")).getId();
        List<MovieDetailsDTO> list =  movieService.getWishListDetails(userId);

        response.setStatus(200);
        return json.writeValueAsString(list);
    }

    @PutMapping(value="/addToWishList/{imdbId}")
    public void addToWishList(@PathVariable String imdbId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        tokenParser.checkToken(request);
        if(request.getAttribute("principal") == null) {
            response.setStatus(401);
            return;
        }

        Integer userId = ((Principal)request.getAttribute("principal")).getId();
        profileService.addToWatchList(userId, imdbId);

        response.setStatus(200);
    }

}
