package com.revature.WebApp.APIAccess;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.WebApp.DTO.RapidMDbSearchResultsDTO;
import com.revature.WebApp.utils.APICallTracker;
import com.revature.WebApp.entities.APICallsEntity;
import com.revature.WebApp.utils.AppProperties;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * RapidAPI Movie Database search pulls from IMDb but offers 1,000 calls/day instead of 200/month.
 * Doesn't have as many possible search criteria and doesn't contain as many details. Does return IMDb ID
 * which can be used to lookup details in OMDb.
 * https://rapidapi.com/rapidapi/api/movie-database-imdb-alternative
 */

@Component
public class RapidMDbAPI {
    private APICallTracker apiTracker;
    private AppProperties appProperties;
    private String URL;
    private String apiKey;
    private String host;


    @Autowired
    private RapidMDbAPI(APICallTracker apiCallTracker, AppProperties appProperties) {
        this.apiTracker = apiCallTracker;
        this.appProperties = appProperties;
        URL = "https://movie-database-imdb-alternative.p.rapidapi.com/";
        //apiKey = AppProperties.getAppProperties().getRapidAPIKey();
        apiKey = appProperties.getSetting("rapidapi.key");
        host = "movie-database-imdb-alternative.p.rapidapi.com";
    }

    /**
     * RapidAPI Movie Database search, get collection of movies and IDMb IDs by title search.
     * @param title - string title of movie
     * @return RapidMDbSearchResultsDTO - object containing a collection of titles matching search string
     * @throws IOException
     */
    public RapidMDbSearchResultsDTO searchByTitle(String title) throws IOException {

        String searchString = URL + "?type=movie&s=" + title + "&page=1&r=json";
        OkHttpClient client = new OkHttpClient();

        APICallsEntity apiCall = new APICallsEntity("RapidMDb", searchString);
        if(apiTracker.trackApiCall(apiCall)) {
            Request request = new Request.Builder()
                    .url(searchString)
                    .get()
                    .addHeader("x-rapidapi-key", apiKey)
                    .addHeader("x-rapidapi-host", host)
                    .build();

            Response response = client.newCall(request).execute();
            String jsonResults = response.body().string();
            jsonResults = jsonResults.replace("imdbID", "ImdbID");
            jsonResults = jsonResults.replace("totalResults", "TotalResults");
            ObjectMapper mapper = new ObjectMapper();
            RapidMDbSearchResultsDTO searchResults = mapper.readValue(jsonResults, RapidMDbSearchResultsDTO.class);

            return searchResults;
        }

        return null;
    }
}
