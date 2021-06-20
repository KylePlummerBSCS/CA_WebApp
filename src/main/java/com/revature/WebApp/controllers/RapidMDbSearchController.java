package com.revature.WebApp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.WebApp.APIAccess.RapidMDbAPI;
import com.revature.WebApp.DTO.RapidMDbSearchResultsDTO;
import com.revature.WebApp.security.TokenParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This API endpoint accesses the RapidAPI MDb to look up movies by title. This alternative
 * to IMDb offers the IMDb ID and allows 1,000 free calls per day instead of 200/month.
 * Access with: ".../rapidSearch/{movieTitle}. Include movie title string.
 * Use this to get IMDb IDs starting with "tt..." by title.
 */
@RestController
public class RapidMDbSearchController {
    private final ObjectMapper json;
    private final RapidMDbAPI apiAccess;
    private final TokenParser tokenParser;

    @Autowired
    public RapidMDbSearchController(RapidMDbAPI api, TokenParser tokenParser) {
        apiAccess = api;
        json = new ObjectMapper();
        this.tokenParser = tokenParser;
    }

    /**
     * This makes the call to the RapidAPI MDb API. Use to produce results that include IMDb ID to get details
     * from OMDb.
     * @param movieTitle - string movie title to search
     * @param response - HTTP response object to answer caller
     * @return - RapidMDb search results JSON - containing a collection of movies matching title.
     * @throws IOException
     */
    @GetMapping(value="/rapidSearch/{movieTitle}", produces = "application/json")
    public String titleSearch(@PathVariable String movieTitle, HttpServletRequest request, HttpServletResponse response) throws IOException {
        RapidMDbSearchResultsDTO searchResultObject = apiAccess.searchByTitle(movieTitle);
        tokenParser.checkToken(request);
        if(request.getAttribute("principal") == null) {
            response.setStatus(401);
            return "Not Authorized.";
        }

        if(searchResultObject == null) {
            response.setStatus(503);
            return "No more calls to RapidMDb API are allowed in this time period.";
        }
        if(searchResultObject.getResponse().equals("False")) {
            response.setStatus(404);
            return "Title not found in RapidMDB Database.";
        }
        response.setStatus(200);
        return json.writeValueAsString(searchResultObject);
    }
}
