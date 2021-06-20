package com.revature.WebApp.APIAccess;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.WebApp.DTO.IMDbSearchResultDTO;
import com.revature.WebApp.entities.APICallsEntity;
import com.revature.WebApp.utils.APICallTracker;
import com.revature.WebApp.utils.AppProperties;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * https://rapidapi.com/amrelrafie/api/movies-tvshows-data-imdb
 */
@Component
public class IMDbAPI {
    private APICallTracker apiTracker;
    private AppProperties appProperties;
    private String URL;
    private String apiKey;
    private String host;
    
    
    @Autowired
    private IMDbAPI(APICallTracker apiCallTracker) {
        this.apiTracker = apiCallTracker;
        this.URL = "https://movies-tvshows-data-imdb.p.rapidapi.com/";
        apiKey = AppProperties.getAppProperties()
                              .getSetting("imdbapi.key");
        host = "movies-tvshows-data-imdb.p.rapidapi.com";
    }
    
    /**
     *
     */
    public IMDbSearchResultDTO searchByPopularity() throws IOException {
        String searchString = URL + "?type=get-popular-movies&page=1&year=";
        OkHttpClient client = new OkHttpClient();
        System.out.println(searchString);
        APICallsEntity apiCall = new APICallsEntity("IMDb", searchString);
        if (apiTracker.trackApiCall(apiCall)) {
            Request request = new Request.Builder().url(searchString).get().addHeader("x-rapidapi-key",apiKey).addHeader("x-rapidapi-host",host).build();
    
            Response response = client.newCall(request).execute();
            String jsonResult = response.body().string();
            jsonResult = jsonResult.replace("Total_results","TotalResult");
            jsonResult =jsonResult.replace("movie_results","Search");
            jsonResult =jsonResult.replace("results","Results");
            jsonResult = jsonResult.replace("status","Status");
            jsonResult = jsonResult.replace("Status_message","StatusMessage");
            jsonResult = jsonResult.replace("imdb_id","ImdbId");
            jsonResult =jsonResult.replace("title","Title");
            jsonResult =jsonResult.replace("year","Year");
            System.out.println();
            ObjectMapper objectMapper = new ObjectMapper();
            IMDbSearchResultDTO searchResults = objectMapper.readValue(jsonResult,IMDbSearchResultDTO.class);
            return searchResults;
            
        }
        
        return null;
    }
}
