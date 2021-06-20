package com.revature.WebApp.APIAccess;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.WebApp.DTO.OMDbSearchResultsDTO;
import com.revature.WebApp.DTO.RapidMDbSearchResultsDTO;
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
 * Open Movie Database API. This API allows 1,000 free calls per day and offers title and IMDb ID search. Returns
 * limited info on a collection of results from title search, offers extensive details from ID lookup.
 * http://omdbapi.com/
 */
@Component
public class OMDbAPI {
    private APICallTracker apiTracker;
    private AppProperties appProperties;
    private String URL;
    private String apiKey;

    @Autowired
    private OMDbAPI(APICallTracker apiCallTracker, AppProperties appProperties) {
        this.apiTracker = apiCallTracker;
        this.appProperties = appProperties;
        apiKey = appProperties.getSetting("omdbapi.key");
        URL = "http://www.omdbapi.com/?apikey=" + apiKey + "&";
    }


    /**
     * Search OMDb by IMDbID to get details about a specific movie.
     * @param imdbId
     * @return OMDbSearchResultsDTO - object containing details of a movie.
     * @throws IOException
     */
    public OMDbSearchResultsDTO searchByImdbId(String imdbId) throws IOException {
        String searchString = URL + "i=" + imdbId;
        OkHttpClient client = new OkHttpClient();

        System.out.println("DEBUG: " + searchString);

        APICallsEntity apiCall = new APICallsEntity("OMDb", searchString);
        if(apiTracker.trackApiCall(apiCall)) {
            Request request = new Request.Builder()
                    .url(searchString)
                    .get()
                    .build();

            Response response = client.newCall(request).execute();
            String jsonResults = response.body().string();
            jsonResults = jsonResults.replace("imdb", "Imdb");
            jsonResults = jsonResults.replace("DVD", "Dvd");
            ObjectMapper mapper = new ObjectMapper();
            OMDbSearchResultsDTO searchResults = mapper.readValue(jsonResults, OMDbSearchResultsDTO.class);
            return searchResults;
        }

        return null;
    }

}
